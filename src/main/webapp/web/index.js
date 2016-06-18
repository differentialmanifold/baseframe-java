$(function() {
	var studentList;

	var ready = function() {
		refresh();
		initEvent();
	};

	var blurEvent = function(event) {
		var $input = $(this);
		var value = $input.val();

		if (value.trim() === '') {
			return;
		}

		var studId = event.data;
		for (var i = 0; i < studentList.length; i++) {
			if (studentList[i].name === value) {
				$input.val('');
				return;
			}
		}
		var student = {};
		student.name = value;
		student.email = value + '@gmail.com';
		if (!studId) {
			add(student);
		} else {
			student.studId = studId;
			update(student);
		}
	};

	var addEvent = function() {
		var $li = $('<li></li>');
		var $input = $('<input type="text"/>')
			.blur(blurEvent)
			.appendTo($li);
		$('#content ul').prepend($li);
	};

	var updateEvent = function() {
		var $update = $(this);
		var studId = $update.data('studId');
		var $li = $update.closest('li');
		$li.children().remove();
		var $input = $('<input type="text"/>')
			.blur(studId, blurEvent)
			.appendTo($li);
	};

	var delEvent = function() {
		var studId = $(this).data('studId');
		del(studId);
	};

	var initEvent = function() {
		$('#add').click(addEvent);
	};

	var refresh = function() {
		$.ajax({
				url: 'services/categoryservice/findAllStudent',
				type: 'GET',
				dataType: 'json'
			})
			.done(function(result) {
				studentList = result.Student;
				studentList.sort(function(a, b) {
					return b.studId - a.studId
				});
				$('#content').empty();
				var $ul = $('<ul class="ul"></ul>')
					.appendTo($('#content'));
				$.each(result.Student, function(index, val) {
					var $li = $('<li></li>')
						.appendTo($ul);
					var $contentName = $('<span>name: ' + val.name + '</span>')
						.appendTo($li);
					var $contentEmail = $('<span>email: ' + val.email + '</span>')
						.appendTo($li);

					var $update = $('<span><input type="button" value="Update"/></span>')
						.data('studId', val.studId)
						.click(updateEvent)
						.appendTo($li);

					var $del = $('<span><input type="button" value="Delete"/></span>')
						.data('studId', val.studId)
						.click(delEvent)
						.appendTo($li);
				});
				console.log("refresh success");
			})
			.fail(function() {
				console.log("refresh error");
			});
	};

	var add = function(student) {
		var data = {
			"Student": {
				"name": student.name,
				"email": student.email
			}
		};
		$.ajax({
				url: 'services/categoryservice/insertStudent',
				data: JSON.stringify(data),
				type: 'POST',
				dataType: 'json',
				contentType: 'application/json'
			})
			.done(function() {
				refresh();
				console.log("add success");
			})
			.fail(function() {
				console.log("add error");
			});
	};

	var update = function(student) {
		var data = {
			"Student": {
				"studId": student.studId,
				"name": student.name,
				"email": student.email
			}
		};
		$.ajax({
				url: 'services/categoryservice/updateStudent',
				data: JSON.stringify(data),
				type: 'POST',
				dataType: 'json',
				contentType: 'application/json'
			})
			.done(function() {
				refresh();
				console.log("add success");
			})
			.fail(function() {
				console.log("add error");
			});
	};

	var del = function(studId) {
		$.ajax({
				url: 'services/categoryservice/deleteStudent/' + studId,
				type: 'POST'
			})
			.done(function(result) {
				refresh();
				console.log("del success");
			})
			.fail(function(result) {
				console.log("del error");
			});
	};
	ready();
})
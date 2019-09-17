$(function(){

    const appendTask = function(data){
        $('#task-list').append();
    };

//    //Loading tasks on load page
//    $.get('/tasks/', function(response)
//    {
//        for(i in response) {
//            appendTask(response[i]);
//        }
//    });

    //Show adding task form
    $('#show-add-task-form').click(function(){
        $('#task-form').css('display', 'flex');

    });

    //Closing adding task form
    $('#task-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none');
        }
    });

    //Show editing task form
      $(document).on('click', '.edit-btn', function(){
        $('#edit-form').css('display', 'flex');
    });

      $("#taskTable").on("click", "tr", function(e) {
        rowCurr = $(e.currentTarget).index();
            var textDescr;
            var textPrior;
            var textId;
            var rowCurr;
            var table = document.getElementById ('taskTable');

          textDescr = table.rows[rowCurr].cells [1].innerText;
          textPrior = table.rows[rowCurr].cells [2].innerHTML;
          textId = table.rows[rowCurr].cells [0].innerHTML;

          document.getElementById('idEdit').innerHTML = textId;
          document.getElementById('descrEdit').value = textDescr;
          document.getElementById('priorEdit').value = textPrior;
      });


    //Closing editing task form
    $('#edit-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none');
        }
    });

    //Getting book
    $(document).on('click', '.task-link', function(){
        var link = $(this);
        var taskId = link.data('id');
        console.log("link " + link);
        console.log("taskId " + taskId);
        $.ajax({
            method: "GET",
            url: '/tasks/' + taskId,
            success: function(response)
            {
                var codeId = response.id;
                var codeDescriptionTask = response.descriptionTask;
                var codePriority = response.priority;

                Txt = "Карточка задания \nЗадание: " + codeDescriptionTask +
                      "\n Описание: "+ codePriority;

                alert(Txt);
            },
            error: function(response)
            {
                if(response.status == 404) {
                    alert('Задание не найдено!');
                }
            }
        });
        return false;
    });

    //Deleting task
    $(document).on('click', '.delete-link', function(){
        var link = $(this);
        var taskId = link.data('id');
        $.ajax({
            method: "DELETE",
            url: '/tasks/' + taskId,
            success: function(response)
            {
              location.reload();
            },
            error: function(response)
            {
                if(response.status == 404) {
                    alert('Задание не найдено!');
                }
            }
        });
        return false;
    });

    //Adding book
    $('#save-task').click(function()
    {
        var data = $('#task-form form').serialize();
        $.ajax({
            method: "POST",
            url: '/tasks/',
            data: data,
            success: function(response)
            {
                $('#task-form').css('display', 'none');
                var task = {};
                task.id = response.id;
                var dataArray = $('#task-form form').serializeArray();
                for(i in dataArray) {
                    task[dataArray[i]['name']] = dataArray[i]['value'];
                }
                 appendTask(task);
                 location.reload();
            }
        });
        return false;
    });

    //Deletig a task
       $(document).on('click', '.delete-btn', function(){
              var link = $(this);
              var taskId = link.data('id');
                  $.ajax({
                      method: "DELETE",
                      url: '/tasks/' + taskId,
                      success: function(response)
                      {
                        location.reload();
                      },
                      error: function(response)
                      {
                          if(response.status == 404) {
                              alert('Задание не найдено!');
                          }
                      }
                  });
                  return false;
              });
//edit task
    $('#edit-task').click(function(){
            var data = $('#edit-form form').serialize();
            var link = $(this);
            var taskId = parseInt(document.getElementById('idEdit').innerHTML);

            $.ajax({
                method: "PUT",
                url: '/tasks/' + taskId,
                data: data,
                success: function(response)
                {
                    $('#edit-form').css('display', 'none');
                    var task = {};
                    task.id = response.id;
                    var dataArray = $('#edit-form form').serializeArray();
                    for(i in dataArray) {
                        task[dataArray[i]['name']] = dataArray[i]['value'];
                    }
                     appendTask(task);
                     location.reload();
                }
            });
        return false;
    });
});
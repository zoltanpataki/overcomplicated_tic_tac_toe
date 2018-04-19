$(document).ready(function () {
    var hits = [null];

    const dom = {
        init: function () {
            console.log("loaded");
            eventApplier.addEventsToButtons();
            eventApplier.addEventToAvatarButton();

        }
    };

    const events = {

        pressButton: function (event) {
            let id = $(event.target).attr("id");
            if ((hits.indexOf(id) > -1) === false){
                ajax.doMove(id);
                hits.push(id);
            }
        },

        drawMove: function (response) {
            let myMove = response.myMove;
            let aiMove = response.aiMove;
            hits.push(aiMove);
            let IWon = response.IWon;
            let aiWon = response.aiWon;
            let hit1 = response.hit1;
            let hit2 = response.hit2;
            let hit3 = response.hit3;

            $("#" + myMove).html(`<i class="fa fa-circle-o" aria-hidden="true"></i>`);
            if (aiMove !== '-1'){
                $("#" + aiMove).html(`<i class="fa fa-times" aria-hidden="true"></i>`);
            }


            if (IWon === "true"){
                $("#" + hit1).attr("class", "btn btn-primary square");
                $("#" + hit2).attr("class", "btn btn-primary square");
                $("#" + hit3).attr("class", "btn btn-primary square");
                $("#squares").unbind();
                hits = [null]
            } else if (aiWon === "true"){
                $("#" + hit1).attr("class", "btn btn-warning square");
                $("#" + hit2).attr("class", "btn btn-warning square");
                $("#" + hit3).attr("class", "btn btn-warning square");
                $("#squares").unbind();
                hits = [null]
            }
        },

        getNewAvatar: function () {
            ajax.getAvatar();
        },

        displayAvatar: function (response) {
            console.log(response);
            $("#avatar").attr("src", response);
        }




    };

    const eventApplier = {

        addEventsToButtons: function () {
            $("#squares").click(events.pressButton);
        },

        addEventToAvatarButton: function () {
            $("#newavatar").click(events.getNewAvatar);
        }

    };

    const ajax = {

        doMove: function (id) {
            $.ajax({
               type: 'POST',
               url: '/api/game-move',
               dataType: 'json',
               data: id,
               contentType: 'application/json',
               success: function (response) {
                   console.log(response);
                  events.drawMove(response);
               },
               error: function (response) {
                   console.log("Wrong move " + response.responseText + " !!!")
               }
            });
        },

        getAvatar: function () {
            $.ajax({
                type: 'GET',
                url: '/api/newavatar',
                success: function (response) {
                    events.displayAvatar(response);
                },
                error: function () {
                    console.log("Something went wrong " + response.responseText + " !!!")
                }
            });
        }
    };

    dom.init();

});
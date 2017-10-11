$(document).ready(function() {

	$(":input").prop("disabled", true)
    $("#updateAccountForm")[0].reset()

	var PasswordsMatchAndAreSufficientLength = function() {
		var valid = true;
		if ($("#password").val() !== $("#passwordconfirmation").val()) {
			console.log("passwords don't match")
			valid = false;
		}
		if	($("#password").val().length < 8) {
			console.log("password not long enough")
			valid = false;
		}
		return valid;
	}

    $(".editAccountIcon").bind("click", function(event) {
        var accountid = event.target.attributes.accountid.value

        $.ajax({
            url: "/account",
			data: { id: accountid },
			type: "GET",
        }).done(function(data) {
            console.log(data)
            $("#updateAccountForm")[0].reset()
			$("#username").prop("value", data.username)
			$("#fullname").prop("value", data.fullName)
			$("#emailaddress").prop("value", data.emailAddress)
            $("#id").prop("value", data.id)

			$(":input").prop("disabled", false)
			$("#username").prop("disabled", true)
        })
    })

    $(".deleteAccountIcon").bind("click", function(event) {
        var accountid = event.target.attributes.accountid.value

        $.ajax({
            url: "/account/remove",
			type: "POST",
            data: { id: accountid },
            success: function(data) {
                $(location).prop("href", "/account")
            }
        })
    })

	$("#submit").bind("click", function(event) {
        event.preventDefault()

		if(PasswordsMatchAndAreSufficientLength()) {
            $.ajax({
                url: "/account",
                data: { fullname: $("#fullname").val(),
                        emailaddress: $("#emailaddress").val(),
                        id: $("#id").val()
                },
                type: "POST",
            }).done(function(data) {
                console.log(data)
                if(data.success == "true") {
                    $(location).attr("href", "/account")
                } else if(data.success == "false") {
                }
            })
        }
	})
})

$(document).ready(function() {

    var changePassword = false

	$(":input").prop("disabled", true)
    $("#createAccountButton").prop("disabled", false)
    $("#updateAccountForm")[0].reset()
    $("#resetPasswordFields").hide()


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

	$("#submitButton").bind("click", function(event) {
        event.preventDefault()

        data = { fullname: $("#fullname").val(),
                     emailaddress: $("#emailaddress").val(),
                     id: $("#id").val() }

        if(changePassword) {
            if(!PasswordsMatchAndAreSufficientLength())
                return

            data.password = $("#password").val()
        }

            $.ajax({
                url: "/account",
                data: data,
                type: "POST",
            }).done(function(data) {
                console.log(data)
                if(data.success == "true") {
                    $(location).attr("href", "/account")
                } else if(data.success == "false") {
                    console.log(data)
                }
            })
	})

    $("#resetPasswordButton").bind("click", function(event) {
        changePassword = !changePassword
        if(changePassword)
            $("#resetPasswordFields").slideDown(500)
        else
            $("#resetPasswordFields").slideUp(500)
    })

	$("#createAccountButton").bind("click", function(event) {
		$(location).prop("href", "/account/create")
	})
})

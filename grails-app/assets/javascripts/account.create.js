$(document).ready(function() {

    var usernameLength = 0
    var passwordLength = 0
    var usernameTouched = false
    var passwordTouched = false

    $("#createForm")[0].reset()
    $("#submitButton").prop("disabled", true)
    $("#passwordRequired").hide()
    $("#usernameRequired").hide()

    var disableSubmitButtonIfFormsInvalid = function() {

        if(usernameLength > 0 && passwordLength > 0) {
            $("#submitButton").prop("disabled", false)
        } else {
            $("#submitButton").prop("disabled", true)
        }
    }

    var validateTouched = function() {
       if(usernameLength > 0)
            usernameTouched = true
       if(passwordLength > 0)
            passwordTouched = true
    }

    var displayMessageWhenInvalidDueToRequired = function() {
        if($("#username").prop("validity")["valueMissing"] && usernameTouched)
            $("#usernameRequired").show()
        else
            $("#usernameRequired").hide()

        if($("#password").prop("validity")["valueMissing"] && passwordTouched)
            $("#passwordRequired").show()
        else
            $("#passwordRequired").hide()

    }

    $("#username").bind("keydown", function(event) {
        usernameLength = $.trim($("#username").val()).length
        validateTouched()
        disableSubmitButtonIfFormsInvalid()
        displayMessageWhenInvalidDueToRequired()
    })

    $("#password").bind("keydown", function(event) {
        passwordLength = $.trim($("#password").val()).length
        validateTouched()
        disableSubmitButtonIfFormsInvalid()
        displayMessageWhenInvalidDueToRequired()
    })

});
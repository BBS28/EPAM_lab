const regexName = /^[\w'\-,.][^0-9_!¡?÷?¿/\\+=@#$%ˆ&*(){}|~<>;:[\]]{1,25}$/;
const regexEmail = /^\S+@\S+\.\S+$/;
const regexLogin = /^[A-Za-z][A-Za-z0-9_]{7,29}$/;
const regexPassword = /(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$/;

const messageName = "Latin letters, minimum 2 characters";
const messageEmail = "Entered value needs to be an e-mail address";
const messageLogin = "Login mast be min 8 letters, numbers and starts with letter";
const messagePassword = "Entered password should be min 8 symbols, 1 capital letter, 1 number";

let validFirstName = false;
let validLastName = false;
let validEmail = false;
let validLogin = false;
let validPassword = false;

$(document).ready(function () {
    $('#first_name').on('input', function () {
        let input = $(this);
        let is_first_name = regexName.test(input.val());
        validFirstName = is_first_name;
        warningHandler(is_first_name, input, "first_name", messageName);
    });

    $('#last_name').on('input', function () {
        let input = $(this);
        let is_last_name = regexName.test(input.val());
        validLastName = is_last_name;
        warningHandler(is_last_name, input, "last_name", messageName);
    });

    $('#email').on('input', function () {
        let input = $(this);
        let is_email = regexEmail.test(input.val());
        validEmail = is_email;
        warningHandler(is_email, input, "email", messageEmail);
    });

    $('#login').on('input', function () {
        let input = $(this);
        let is_login = regexLogin.test(input.val());
        validLogin = is_login;
        warningHandler(is_login, input, "login", messageLogin);
    });

    $('#password').on('input', function () {
        let input = $(this);
        let is_password = regexPassword.test(input.val());
        validPassword = is_password;
        warningHandler(is_password, input, "password", messagePassword);
    });

});

let warningHandler = function (is_correct, input, id, message) {
    if (is_correct) {
        input.removeClass("invalid").addClass("valid");
        $("#error_" + id).html("");
    } else {
        input.removeClass("valid").addClass("invalid");
        $("#error_" + id).html(message);
    }
};

jQuery(function($) {
    $('#reg-form').on('submit', function (event) {
        if (!validateForm()) {
            event.preventDefault();
        }
    });
    function validateForm() {
        return (validFirstName && validLastName && validEmail && validLogin && validPassword)
    }
});
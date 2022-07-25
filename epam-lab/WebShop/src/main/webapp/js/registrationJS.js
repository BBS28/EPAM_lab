document.addEventListener(
    "DOMContentLoaded",

    function () {
        const form = document.getElementById("reg-form")[0];

        const firsName = document.getElementById("first_name");
        const lastName = document.getElementById("last_name");
        const email = document.getElementById("email");
        const login = document.getElementById("login");
        const password = document.getElementById("password");

        const firstNameError = document.querySelector("input#first_name + span.error");
        const lastNameError = document.querySelector("input#last_name + span.error");
        const emailError = document.querySelector("input#email + span.error");
        const loginError = document.querySelector("input#login + span.error");
        const passwordError = document.querySelector("input#password + span.error");

        const input = "input";
        const error = "error";
        const errorActive = "error active";


        firsName.addEventListener(input, function (event) {
            if (firsName.validity.valid) {
                firstNameError.textContent = "";
                firstNameError.className = error;
            } else {
                showFirstNameError();
            }
        });

        lastName.addEventListener(input, function (event) {
            if (lastName.validity.valid) {
                lastNameError.textContent = "";
                lastNameError.className = error;
            } else {
                showLastNameError();
            }
        });

        email.addEventListener(input, function (event) {
            if (email.validity.valid) {
                emailError.textContent = "";
                emailError.className = error;
            } else {
                showEmailError();
            }
        });

        login.addEventListener(input, function (event) {
            if (login.validity.valid) {
                loginError.textContent = "";
                loginError.className = error;
            } else {
                showLoginError();
            }
        });

        password.addEventListener(input, function (event) {
            if (password.validity.valid) {
                passwordError.textContent = "";
                passwordError.className = error;
            } else {
                showPasswordError();
            }
        });

        form.addEventListener("submit", function (event) {
            if (!firsName.validity.valid ||
                !lastName.validity.valid ||
                !email.validity.valid ||
                !login.validity.valid ||
                !password.validity.valid) {
                event.preventDefault();
            }
        });

        function showFirstNameError() {
            if (firsName.validity.valueMissing) {
                firstNameError.textContent = "You need to enter the first name";
            } else if (firsName.validity.patternMismatch) {
                firstNameError.textContent = "Enter correct first name";
            }
            firstNameError.className = errorActive;
        }

        function showLastNameError() {
            if (lastName.validity.valueMissing) {
                lastNameError.textContent = "You need to enter the last name";
            } else if (lastName.validity.patternMismatch) {
                lastNameError.textContent = "Enter correct last name";
            }
            lastNameError.className = errorActive;
        }

        function showEmailError() {
            if (email.validity.valueMissing) {
                emailError.textContent = "You need to enter an e-mail address";
            } else if (email.validity.patternMismatch) {
                emailError.textContent = "Entered value needs to be an e-mail address";
            }

            emailError.className = errorActive;
        }

        function showLoginError() {
            if (login.validity.valueMissing) {
                loginError.textContent = "You need to enter a login";
            } else if (login.validity.patternMismatch) {
                loginError.textContent = "Login mast be min 8 letters, numbers and starts with letter";
            }

            loginError.className = errorActive;
        }

        function showPasswordError() {
            if (password.validity.valueMissing) {
                passwordError.textContent = "You need to enter the password";
            } else if (password.validity.patternMismatch) {
                passwordError.textContent = "Entered password should be min 8 symbols, 1 capital letter, 1 number";
            }
            passwordError.className = errorActive;
        }

    },
    false
);



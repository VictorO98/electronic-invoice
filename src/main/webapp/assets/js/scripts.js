// Function to set toggle menu
function toggleMenu() {
    const menu = document.getElementById('menu');
    menu.style.display = (menu.style.display === 'block' ? 'none' : 'block');
}

// Function to show section menu electronic invoice
function showSection(sectionId) {
    const sections = document.querySelectorAll('.section');
    sections.forEach(section => section.classList.remove('active'));

    const selectedSection = document.getElementById(sectionId);
    selectedSection.classList.add('active');

    // Cierra el menú después de hacer la selección
    const menu = document.getElementById('menu');
    menu.style.display = 'none';
}

// Toast That allow or not add new ids invoices
document.addEventListener("DOMContentLoaded", function () {
    const maxFields = 5; // Límite de 5 campos
    const dynamicFields = document.getElementById("dynamic-fields-charge");
    const addFieldButton = document.getElementById("add-field-charge");

    // Toastr configuration
    toastr.options = {
        "closeButton": false,
        "debug": false,
        "newestOnTop": false,
        "progressBar": false,
        "positionClass": "toast-top-right",
        "preventDuplicates": false,
        "onclick": null,
        "showDuration": "300",
        "hideDuration": "1000",
        "timeOut": "5000",
        "extendedTimeOut": "1000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    };

    addFieldButton.addEventListener("click", function () {
        const fieldCount = dynamicFields.querySelectorAll(".input-group").length;

        if (fieldCount < maxFields) {
            const newField = document.createElement("div");
            newField.classList.add("input-group");
            newField.innerHTML = `
                    <input type="number" name="ids-invoices[]" placeholder="Id Factura" required class="input-large">     
                    <button type="button" class="delete-button remove-field" style="margin-left: 10px;">x</button> 
                `;

            dynamicFields.appendChild(newField);

            newField.querySelector(".remove-field").addEventListener("click", function () {
                newField.remove();
            });
        } else {
            toastr["error"]("Solo puedes añadir hasta 5 campos.", "Error");
        }
    });

    // Show the active section if it's defined
    if (activeSection) {
        showSection(activeSection);
    }
});

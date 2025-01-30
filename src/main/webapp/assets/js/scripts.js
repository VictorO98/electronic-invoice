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
    console.log('Botón de agregar JSON presionado');
    const maxFields = 5;
    const dynamicFieldsCharge = document.getElementById("dynamic-fields-charge");
    const addFieldButtonCharge = document.getElementById("add-field-charge");
    const dynamicFieldsJson = document.getElementById("dynamic-fields-json");
    const addFieldButtonJson = document.getElementById("add-field-json");

    // Function to add new field in id invoice
    function addInvoiceField(container) {
        const fieldCount = container.querySelectorAll(".input-group").length;

        if (fieldCount < maxFields) {
            const newField = document.createElement("div");
            newField.classList.add("input-group");
            newField.innerHTML = `
                <input type="number" name="ids-invoices[]" placeholder="Id Factura" required class="input-large">
                <button type="button" class="delete-button remove-field" style="margin-left: 10px;">x</button>
            `;

            container.appendChild(newField);
        } else {
            alert("Solo puedes añadir hasta 5 campos.", "Error");
        }
    }

    // Event to add new dynamic fields in both sections
    addFieldButtonCharge.addEventListener("click", function () {
        addInvoiceField(dynamicFieldsCharge);
    });

    addFieldButtonJson.addEventListener("click", function () {
        addInvoiceField(dynamicFieldsJson);
    });

    // Delegate events to delete fields
    document.addEventListener("click", function (event) {
        if (event.target.classList.contains("remove-field")) {
            event.target.closest(".input-group").remove();
        }
    });

    // Show the active section if it's defined
    if (activeSection) {
        showSection(activeSection);
    }
});

// Dark Mode Toggle
function toggleDarkMode() {
    document.body.classList.toggle('dark-mode');
}

// Slot Selection Logic
function createSlot(slotId, type) {
    const slot = document.createElement('div');
    slot.className = 'slot';
    slot.textContent = `Slot ${slotId}`;
    slot.id = `${type}-slot-${slotId}`;

    slot.addEventListener('click', () => {
        // Clear previous selections
        document.querySelectorAll('.slot').forEach(s => s.classList.remove('selected'));
        // Mark current slot as selected
        slot.classList.add('selected');
    });

    return slot;
}

// Generate Slots
function generateSlots(slotCount, containerId, type) {
    const container = document.getElementById(containerId);
    for (let i = 1; i <= slotCount; i++) {
        container.appendChild(createSlot(i, type));
    }
}

// Real-Time EV Charging Slot Status
function updateEVSlotStatus(slotId, status) {
    document.getElementById(`ev-slot-${slotId}-status`).textContent = status;
}

// Simulate EV Slot Status Updates
setInterval(() => {
    updateEVSlotStatus(1, Math.random() > 0.5 ? 'Available' : 'Charging');
    updateEVSlotStatus(2, Math.random() > 0.5 ? 'Available' : 'Charging');
}, 5000);

// Admin Reset Functionality
function resetSlots() {
    document.querySelectorAll('.slot').forEach(slot => slot.classList.remove('selected'));
    alert('All slots have been reset.');
}

// Admin Dashboard Slot Population
function populateAdminDashboard() {
    const adminTable = document.getElementById('admin-slot-table');
    adminTable.innerHTML = '';
    for (let i = 1; i <= 4; i++) { // Normal slots
        adminTable.innerHTML += `<tr><td>Slot ${i}</td><td>Available</td><td>Normal</td></tr>`;
    }
    for (let i = 1; i <= 2; i++) { // EV slots
        adminTable.innerHTML += `<tr><td>EV Slot ${i}</td><td>Available</td><td>EV</td></tr>`;
    }
}

// Initialize Slots and Dashboard
document.addEventListener('DOMContentLoaded', () => {
    generateSlots(4, 'normal-slots', 'normal');
    generateSlots(2, 'ev-slots', 'ev');
    populateAdminDashboard();
});
const express = require('express');
const mongoose = require('mongoose');
const bodyParser = require('body-parser');
const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');
const cors = require('cors');
const app = express();

// Middleware
app.use(bodyParser.json());
app.use(cors()); // Allow cross-origin requests

// Database connection (MongoDB)
mongoose.connect('mongodb://localhost/smart_parking_system', { useNewUrlParser: true, useUnifiedTopology: true })
  .then(() => console.log('Connected to MongoDB'))
  .catch(err => console.error('Error connecting to MongoDB:', err));

// Models
const Admin = mongoose.model('Admin', new mongoose.Schema({
  username: { type: String, required: true },
  password: { type: String, required: true }
}));

const Slot = mongoose.model('Slot', new mongoose.Schema({
  slotNumber: { type: Number, required: true },
  status: { type: String, default: 'available' } // 'available' or 'booked'
}));

// Helper Function: Authenticate Admin
const authenticateToken = (req, res, next) => {
  const token = req.headers['authorization']?.split(' ')[1];
  if (!token) return res.status(401).json({ message: 'Unauthorized access' });

  jwt.verify(token, 'secretkey', (err, decoded) => {
    if (err) return res.status(403).json({ message: 'Invalid token' });
    req.userId = decoded.userId;
    next();
  });
};

// Routes

// Admin Login (Authentication)
app.post('/login', async (req, res) => {
  const { username, password } = req.body;
  try {
    const admin = await Admin.findOne({ username });
    if (!admin) {
      return res.status(400).json({ message: 'Invalid username or password' });
    }

    const isMatch = await bcrypt.compare(password, admin.password);
    if (!isMatch) {
      return res.status(400).json({ message: 'Invalid username or password' });
    }

    const token = jwt.sign({ userId: admin._id }, 'secretkey', { expiresIn: '1h' });
    res.json({ token });
  } catch (error) {
    console.error('Error during login:', error);
    res.status(500).json({ message: 'Server error. Please try again.' });
  }
});

// Fetch all slots (real-time)
app.get('/get-slots', async (req, res) => {
  try {
    const slots = await Slot.find();
    res.json(slots);
  } catch (error) {
    console.error('Error fetching slots:', error);
    res.status(500).json({ message: 'Unable to fetch slots' });
  }
});

// Book a slot
app.post('/book-slot', async (req, res) => {
  const { slotId } = req.body;
  try {
    const slot = await Slot.findById(slotId);
    if (!slot) {
      return res.status(404).json({ message: 'Slot not found' });
    }
    if (slot.status === 'booked') {
      return res.status(400).json({ message: 'Slot is already booked' });
    }

    slot.status = 'booked';
    await slot.save();
    res.json({ message: 'Slot booked successfully' });
  } catch (error) {
    console.error('Error booking slot:', error);
    res.status(500).json({ message: 'Unable to book slot' });
  }
});

// Reset all slots
app.post('/reset-slots', authenticateToken, async (req, res) => {
  try {
    await Slot.updateMany({}, { status: 'available' });
    res.json({ message: 'All slots reset to available' });
  } catch (error) {
    console.error('Error resetting slots:', error);
    res.status(500).json({ message: 'Unable to reset slots' });
  }
});

// Seed Slots for Testing
app.post('/seed-slots', async (req, res) => {
  try {
    await Slot.deleteMany(); // Clear existing slots
    const slots = [];
    for (let i = 1; i <= 10; i++) {
      slots.push({ slotNumber: i });
    }
    await Slot.insertMany(slots);
    res.json({ message: 'Slots seeded successfully' });
  } catch (error) {
    console.error('Error seeding slots:', error);
    res.status(500).json({ message: 'Unable to seed slots' });
  }
});

// Update Slot Status (for Admin)
app.post('/slots/:id', authenticateToken, async (req, res) => {
  try {
    const slot = await Slot.findById(req.params.id);
    if (!slot) return res.status(404).json({ message: 'Slot not found' });

    slot.status = slot.status === 'available' ? 'booked' : 'available';
    await slot.save();

    res.json({ message: 'Slot status updated', slot });
  } catch (error) {
    console.error('Error updating slot status:', error);
    res.status(500).json({ message: 'Unable to update slot status' });
  }
});

// Start Server
app.listen(3000, () => {
  console.log('Server running on http://localhost:3000');
});

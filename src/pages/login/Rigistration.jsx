import React, { useState } from 'react';
import axios from 'axios';
import "./res.scss";

function RegisterPage() {
  const [formData, setFormData] = useState({
    // Initialize form data fields
    userName: '',
    userEmail: '',
    aboutUser: '',
    gender: '',
    dateOfBirth: '',
    role: 'Admin_Role', // Assuming Admin role by default
    userPassword: '',
    userProfile: ''
  });

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/Admin', formData);
      console.log('Response from backend:', response.data);
      // Handle success response
    } catch (error) {
      console.error('Error sending data:', error);
      // Handle error response
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prevState => ({
      ...prevState,
      [name]: value
    }));
  };

  return (
    <div className="register-container">
      <h1 className="register-title">Create Your Account</h1>
      <form onSubmit={handleSubmit} className="register-form">
        <input type="text" name="userName" value={formData.userName} onChange={handleChange} placeholder="Username" className="input-field" />
        <input type="email" name="userEmail" value={formData.userEmail} onChange={handleChange} placeholder="Email" className="input-field" />
        <textarea name="aboutUser" value={formData.aboutUser} onChange={handleChange} placeholder="Tell us about yourself" className="input-field text-area"></textarea>
        <select name="gender" value={formData.gender} onChange={handleChange} className="input-field">
          <option value="">Select Gender</option>
          <option value="Male">Male</option>
          <option value="Female">Female</option>
          <option value="Other">Other</option>
        </select>
        <input type="date" name="dateOfBirth" value={formData.dateOfBirth} onChange={handleChange} className="input-field" />
        <input type="password" name="userPassword" value={formData.userPassword} onChange={handleChange} placeholder="Password" className="input-field" />
        <input type="text" name="userProfile" value={formData.userProfile} onChange={handleChange} placeholder="Profile URL" className="input-field" />
        <button type="submit" className="register-button">Register</button>
      </form>
    </div>
  );
}

export default RegisterPage;

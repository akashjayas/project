import React, { useState } from 'react';
import './EditProfilePage.css';
 
const EditProfilePage = ({ userInfo, setUserInfo, setProfilePhoto, setCoverPhoto, onClose }) => {
  const [newName, setNewName] = useState(userInfo.name);
  const [newGender, setNewGender] = useState(userInfo.gender);
  const [newDateOfBirth, setNewDateOfBirth] = useState(userInfo.dateOfBirth);
  const [newEmail, setNewEmail] = useState(userInfo.email);
  const [newLocation, setNewLocation] = useState(userInfo.location);
  const [newProfilePhoto, setNewProfilePhoto] = useState('');
  const [newCoverPhoto, setNewCoverPhoto] = useState('');
 
  const handleSaveChanges = () => {
    // Update user information
    setUserInfo({
      name: newName,
      gender: newGender,
      dateOfBirth: newDateOfBirth,
      email: newEmail,
      location: newLocation,
    });
 
    // Update profile and cover photos with new URLs
    setProfilePhoto(newProfilePhoto || 'https://example.com/default-profile-photo.jpg');
    setCoverPhoto(newCoverPhoto || 'https://example.com/default-cover-photo.jpg');
   
    // Implement logic to save changes
    console.log('Changes saved');
   
    // Close the Edit Profile box
    onClose();
  };
 
  return (
    <div className="edit-profile-container">
      <h2>Edit Profile</h2>
      <div>
        <label>Name:</label>
        <input
          type="text"
          value={newName}
          onChange={(e) => setNewName(e.target.value)}
        />
      </div>
      <div>
        <label>Gender:</label>
        <input
          type="text"
          value={newGender}
          onChange={(e) => setNewGender(e.target.value)}
        />
      </div>
      <div>
        <label>Date of Birth:</label>
        <input
          type="date"
          value={newDateOfBirth}
          onChange={(e) => setNewDateOfBirth(e.target.value)}
        />
      </div>
      <div>
        <label>Email:</label>
        <input
          type="email"
          value={newEmail}
          onChange={(e) => setNewEmail(e.target.value)}
        />
      </div>
      <div>
        <label>Location:</label>
        <input
          type="text"
          value={newLocation}
          onChange={(e) => setNewLocation(e.target.value)}
        />
      </div>
      <div>
        <label>Profile Photo URL:</label>
        <input
          type="text"
          value={newProfilePhoto}
          onChange={(e) => setNewProfilePhoto(e.target.value)}
        />
      </div>
      <div>
        <label>Cover Photo URL:</label>
        <input
          type="text"
          value={newCoverPhoto}
          onChange={(e) => setNewCoverPhoto(e.target.value)}
        />
      </div>
      <button onClick={handleSaveChanges}>Save Changes</button>
    </div>
  );
};
 
export default EditProfilePage;
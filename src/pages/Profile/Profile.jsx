
import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import './UserProfilePage.css';
import EditProfilePage from './EditProfilePage';
 
const Profile = () => {
  const [profilePhoto, setProfilePhoto] = useState('https://imgs.search.brave.com/lpTOG0vWDmPrP3sxUe2Ei31XQRA0jqKG74M2FspBO5c/rs:fit:860:0:0/g:ce/aHR0cHM6Ly9tZWRp/YS5pc3RvY2twaG90/by5jb20vaWQvNDc3/MzA0MzY2L3Bob3Rv/L3Byb2ZpbGUuanBn/P3M9NjEyeDYxMiZ3/PTAmaz0yMCZjPVhT/LXdocjM0WnpILWhN/WUpXX1F5Uy0td1o5/aGNhYWp0cDJZSzd6/WmZuY0E9');
  const [coverPhoto, setCoverPhoto] = useState('https://imgs.search.brave.com/bN8CKwyKnay8oRl1rfL9SV3rM2VfNCBKeTEpStbAobQ/rs:fit:860:0:0/g:ce/aHR0cHM6Ly93YWxs/cGFwZXJjYXZlLmNv/bS93cC9XYk9DTXhV/LmpwZw');
  const [isEditProfileOpen, setIsEditProfileOpen] = useState(false);
 
  // User Information
  const [userInfo, setUserInfo] = useState({
    name: 'BhavyaSree Tippa',
    gender: 'female',
    dateOfBirth: '2002/05/29', // Assuming date format YYYY-MM-DD
    email: 'VDD@gmail.com',
    location: 'BMVRM, AP',
  });
 
  const handleLogout = () => {
    // Implement your logout logic here
    console.log('Logout');
  };
 
  const handleEditProfileToggle = () => {
    setIsEditProfileOpen(!isEditProfileOpen);
  };
 
  return (
    <div className="profile-container">
      <div className="cover-photo" style={{ backgroundImage: `url(${coverPhoto})` }}>
        <div className="profile-header">
          <img src={profilePhoto} alt="Profile" className="profile-photo" />
        </div>
       
      </div>
      <div className="profile-info">
            <h2>{userInfo.name}</h2>
            <p>{userInfo.gender}</p>
            <p>Age: {calculateAge(userInfo.dateOfBirth)}</p>
            <p>Email: {userInfo.email}</p>
            <p>Location: {userInfo.location}</p>
            <div className="buttons">
          <button onClick={handleEditProfileToggle} className="edit-profile-button">
              Edit Profile
            </button>
          </div>
          
          </div>
         
          <div className="buttons1">
          <div className="profile-buttons">
            
            <button onClick={handleLogout} className="logout-button">
              Logout
            </button>
          </div>
          </div>
      {isEditProfileOpen && (
        <EditProfilePage
          userInfo={userInfo}
          setUserInfo={setUserInfo}
          setProfilePhoto={setProfilePhoto}
          setCoverPhoto={setCoverPhoto}
          onClose={() => setIsEditProfileOpen(false)}
        />
      )}
    </div>
  
  );
};
 
export default Profile;
 

function calculateAge(dateOfBirth) {
  const birthDate = new Date(dateOfBirth);
  const currentDate = new Date();
  const age = currentDate.getFullYear() - birthDate.getFullYear();
 
  // Adjust age if birthday hasn't occurred yet this year
  if (currentDate.getMonth() < birthDate.getMonth() ||
      (currentDate.getMonth() === birthDate.getMonth() && currentDate.getDate() < birthDate.getDate())) {
    return age - 1;
  }
 
  return age;
}
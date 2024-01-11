import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom'; // Import useParams from react-router-dom
import Sidebar from '../../components/sidebar/Sidebar';
import Navbar from '../../components/navbar/Navbar';
import Chart from '../../components/chart/Chart';
import List from '../../components/table/Table';
import axios from 'axios';
import './single.scss';

const Single = () => {
  const [userData, setUserData] = useState({
    userId: null,
    userName: '',
    userEmail: '',
    aboutUser: '',
    role: '',
    userPassword: '',
  });

  const { userId } = useParams(); // Use useParams to get the dynamic parameter from the URL

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(`http://localhost:8099/Admin/users/${userId}`); // Use the dynamic userId

        setUserData(response.data);
      } catch (error) {
        console.error('Error fetching user data:', error);
      }
    };

    fetchData();
  }, [userId]); // Include userId in the dependency array to fetch data when it changes

  return (
    <div className="single">
      <Sidebar />
      <div className="singleContainer">
        <Navbar />
        <div className="top">
          <div className="left">
            <div className="editButton">Edit</div>
            <h1 className="title">Information</h1>
            <div className="item">
              <img
                src="https://th.bing.com/th/id/OIP.GBr40Q20rvF26VKUawbjtAHaHa?w=980&h=980&rs=1&pid=ImgDetMain"
                alt=""
                className="itemImg"
              />
              <div className="details">
                <h1 className="itemTitle">{userData.userName}</h1>
                <div className="detailItem">
                  <span className="itemKey">Email:</span>
                  <span className="itemValue">{userData.userEmail}</span>
                </div>
                <div className="detailItem">
                  <span className="itemKey">About User:</span>
                  <span className="itemValue">{userData.aboutUser}</span>
                </div>
                <div className="detailItem">
                  <span className="itemKey">Role:</span>
                  <span className="itemValue">{userData.role}</span>
                </div>
              </div>
            </div>
          </div>
          {/* <div className="right">
            <Chart aspect={3 / 1} title="User Spending ( Last 6 Months)" />
          </div> */}
        </div>
        <div className="bottom">
          <h1 className="title">Events List</h1>
          <List />
        </div>
      </div>
    </div>
  );
};

export default Single;

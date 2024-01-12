import React, { useState } from 'react';
import axios from 'axios';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import './newEventForm.scss';
import Navbar from "../../components/navbar/Navbar";
import Sidebar from "../../components/sidebar/Sidebar";

const NewEventForm = () => {
  const [eventData, setEventData] = useState({
    eventName: '',
    location: '',
    startDate: '',
    endDate: '',
    description: '',
    eventStatus: '',
    peopleCount: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setEventData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      await axios.post('http://localhost:8099/Admin/events', eventData);
      // Display success notification
      toast.success('Event added successfully!', {
        position: 'top-right',
        autoClose: 3000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
      });
    } catch (error) {
      // Display error notification
      toast.error('Error adding event. Please check your input and try again.', {
        position: 'top-right',
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
      });
      console.error('Error creating event:', error);
    }
  };

  return (
    <div className="list">
            <Sidebar />
            <div className="listContainer">
                <Navbar />
    <div className="newEventForm">
      <h2>Add New Event</h2>
            <form onSubmit={handleSubmit}>
                <label>
                    Event Name:
                    <input
                        type="text"
                        name="eventName"
                        value={eventData.eventName}
                        onChange={handleChange}
                        required
                    />
                </label>
                <label>
                    Location:
                    <input
                        type="text"
                        name="location"
                        value={eventData.location}
                        onChange={handleChange}
                        required
                    />
                </label>
                <label>
                    Start Date:
                    <input
                        type="datetime-local"
                        name="startDate"
                        value={eventData.startDate}
                        onChange={handleChange}
                        required
                    />
                </label>
                <label>
                    End Date:
                    <input
                        type="datetime-local"
                        name="endDate"
                        value={eventData.endDate}
                        onChange={handleChange}
                        required
                    />
                </label>
                <label>
                    Description:
                    <textarea
                        name="description"
                        value={eventData.description}
                        onChange={handleChange}
                        required
                    />
                </label>
                <label>
                    Event Status:
                    <input
                        type="text"
                        name="eventStatus"
                        value={eventData.eventStatus}
                        onChange={handleChange}
                        required
                    />
                </label>
                <label>
                    People Count:
                    <input
                        type="number"
                        name="peopleCount"
                        value={eventData.peopleCount}
                        onChange={handleChange}
                        required
                    />
                </label>

                <button type="submit">Create Event</button>
            </form>
            <ToastContainer />
    </div>
    </div>
    </div>
  );
};

export default NewEventForm;

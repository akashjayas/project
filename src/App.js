import Home from "./pages/home/Home";
import Login from "./pages/login/Login";
import Logout from "./pages/logout/Logout";
import List from "./pages/list/List";
import Event from "./pages/event/Event";
import Single from "./pages/single/Single";
import New from "./pages/new/New";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { productInputs, userInputs } from "./formSource";
import "./style/dark.scss";
import { useContext } from "react";
import { DarkModeContext } from "./context/darkModeContext";
import Profile from "./pages/Profile/Profile";
import NewEventForm from "./pages/event/NewEventForm";
import EventDetails from "./pages/event/EventDetails";

function App() {
  const { darkMode } = useContext(DarkModeContext);

  return (
    <div className={darkMode ? "app dark" : "app"}>
      <BrowserRouter>
        <Routes>
          <Route path="/">
            <Route index element={<Home />} />
            <Route path="login" element={<Login />} />
            <Route path="logout" element={<Logout />} />
            <Route path="Profile" element={<Profile />} />
            <Route path="NewEventForm" element={<NewEventForm />} />
        
            <Route path="users">
              <Route index element={<List />} />
              <Route path=":userId" element={<Single />} />
              <Route
                path="new"
                element={<New inputs={userInputs} title="Add New User" />}
              />
            </Route>
            <Route path="Event">
              <Route index element={<Event />} />
              <Route path=":eventId" element={<EventDetails />} />
              <Route
                path="new"
                element={<New inputs={productInputs} title="Add New Product" />}
              />
              
            </Route>
          </Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;

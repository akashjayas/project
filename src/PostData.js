import axios from "axios";
import axiosInstance from "./pages/login/axiosinstance";


export const postEvent=async (formData)=>{
    try{
        await axiosInstance.post("/Admin/events", formData, {headers:{'Content-Type':'multipart/form-data',},}).then((response)=>{
        console.log(response); 
        if(response.status===201){
            alert(response.data);
            window.location.reload();
        }   
        return response});
    }catch(error){
        console.error("error on event posting :",error);
        return error.response;
    }
}

export const postSpot=async (formData)=>{
    try{
        await axiosInstance.post("/Admin/touristSpot",formData,{headers:{'Content-Type':'multipart/form-data',},}).then((response)=>{
        console.log(response);
        if(response.status===201){
            alert(response.data);
            window.location.reload();
        }    
        return (response)});
    }catch(error){
        console.error("error on posting spot :", error);
        return error.response;
    }
}
import React, { useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";

const StudentForm = ({ addStudent }) => {
  const [name, setName] = useState("");
  const [course, setCourse] = useState("");
  const [picture, setPicture] = useState(null);
  const [preview, setPreview] = useState("");

  const courseOptions = ["BSIT", "BSCS", "BSBA", "BSA", "BSED", "BSN", "BSEE"];

  const handlePictureChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      setPicture(file);
      // Create a preview URL for the image
      const reader = new FileReader();
      reader.onloadend = () => {
        setPreview(reader.result);
      };
      reader.readAsDataURL(file);
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (name && course) {
      addStudent(name, course, preview); // Pass the preview URL instead of file
      setName("");
      setCourse("");
      setPicture(null);
      setPreview("");
    }
  };

  return (
    <form onSubmit={handleSubmit} className="p-4 bg-dark text-light rounded shadow w-50 mx-auto">
      {/* Student Name Input */}
      <div className="mb-3">
        <input
          type="text"
          className="form-control"
          placeholder="Enter Student Name"
          value={name}
          onChange={(e) => setName(e.target.value)}
          required
        />
      </div>

      {/* Course Dropdown */}
      <div className="mb-3">
        <select 
          className="form-select" 
          value={course} 
          onChange={(e) => setCourse(e.target.value)} 
          required
        >
          <option value="" disabled>Select a Course</option>
          {courseOptions.map((option) => (
            <option key={option} value={option}>{option}</option>
          ))}
        </select>
      </div>

      {/* Picture Upload */}
      <div className="mb-3">
        <label htmlFor="picture" className="form-label">School Logo</label>
        <input
          type="file"
          className="form-control"
          id="picture"
          accept="image/*"
          onChange={handlePictureChange}
        />
        {preview && (
          <div className="mt-2">
            <img 
              src={preview} 
              alt="Preview" 
              style={{ width: "100px", height: "100px", objectFit: "cover" }} 
            />
          </div>
        )}
      </div>

      {/* Submit Button */}
      <button type="submit" className="btn btn-warning w-50 fw-bold">
        Add Student
      </button>
    </form>
  );
};

export default StudentForm;
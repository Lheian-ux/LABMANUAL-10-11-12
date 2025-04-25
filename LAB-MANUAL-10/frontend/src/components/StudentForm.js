import React, { useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css"; // Ensure Bootstrap is imported

const StudentForm = ({ addStudent }) => {
  const [name, setName] = useState("");
  const [course, setCourse] = useState("");

  const courseOptions = ["BSIT", "BSCS", "BSBA", "BSA", "BSED", "BSN", "BSEE"];

  const handleSubmit = (e) => {
    e.preventDefault();
    if (name && course) {
      addStudent(name, course);
      setName("");
      setCourse("");
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
        <select className="form-select" value={course} onChange={(e) => setCourse(e.target.value)} required>
          <option value="" disabled>Select a Course</option>
          {courseOptions.map((option) => (
            <option key={option} value={option}>{option}</option>
          ))}
        </select>
      </div>

      {/* Submit Button */}
      <button type="submit" className="btn btn-warning w-50 fw-bold">
        Add Student
      </button>
    </form>
  );
};

export default StudentForm;
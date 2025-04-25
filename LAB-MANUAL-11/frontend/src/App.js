import React, { useState, useEffect } from "react";
import StudentForm from "./components/StudentForm";
import StudentList from "./components/StudentList";
import "./App.css";

const App = () => {
  const [students, setStudents] = useState([]);
  const [darkMode, setDarkMode] = useState(false);

  // Load students from localStorage on component mount
  useEffect(() => {
    const savedStudents = localStorage.getItem('students');
    if (savedStudents) {
      try {
        const parsedStudents = JSON.parse(savedStudents);
        // Filter out any corrupted entries
        const validStudents = parsedStudents.filter(student => 
          student.id && student.name && student.course
        );
        setStudents(validStudents);
      } catch (error) {
        console.error("Error parsing saved students:", error);
        localStorage.removeItem('students');
      }
    }
  }, []);

  // Save students to localStorage whenever they change
  useEffect(() => {
    localStorage.setItem('students', JSON.stringify(students));
  }, [students]);

  // Add student to the list
  const addStudent = (name, course, picture) => {
    const newStudent = {
      id: Date.now(), // Using timestamp as simple unique ID
      name,
      course,
      picture: picture || null // Store the image data URL or null if no picture
    };
    setStudents(prevStudents => [...prevStudents, newStudent]);
  };

  // Delete student from the list
  const deleteStudent = (id) => {
    setStudents(prevStudents => prevStudents.filter(student => student.id !== id));
  };

  // Toggle theme
  const toggleTheme = () => {
    setDarkMode(prevMode => !prevMode);
  };

  // Apply theme to body
  useEffect(() => {
    document.body.className = darkMode ? "dark-mode" : "light-mode";
  }, [darkMode]);

  // Clear all students (added functionality)
  const clearAllStudents = () => {
    if (window.confirm("Are you sure you want to delete all students?")) {
      setStudents([]);
    }
  };

  return (
    <div className="container py-4">
      <h1 className="text-center mb-4">Student Recording System</h1>
      
      <div className="text-center mb-4">
        <button onClick={toggleTheme} className="btn btn-secondary me-2">
          {darkMode ? "☀️" : "🌙"}
        </button>
        
        {students.length > 0 && (
          <button onClick={clearAllStudents} className="btn btn-danger">
            🗑️ Clear All Students
          </button>
        )}
      </div>
      
      <StudentForm addStudent={addStudent} />
      
      {students.length > 0 ? (
        <StudentList students={students} deleteStudent={deleteStudent} />
      ) : (
        <div className="text-center mt-5">
          <h4>No students added yet</h4>
          <p className="text-muted">Add your first student using the form above</p>
        </div>
      )}
    </div>
  );
};

export default App;
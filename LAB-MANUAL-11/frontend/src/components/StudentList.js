import React from "react";

const StudentList = ({ students, deleteStudent }) => {
  return (
    <div className="mt-4 w-100">
      <h2 className="text-center mb-4">Student Record</h2>
      <div className="row justify-content-center">
        {students.map((student) => (
          <div key={student.id} className="col-md-8 mb-3">
            <div className="card student-card h-100">
              <div className="student-content-wrapper">
                {student.picture && (
                  <div className="student-image-container">
                    <img 
                      src={student.picture} 
                      alt={student.name}
                      className="student-image"
                    />
                  </div>
                )}
                <div className="student-text-content">
                  <h5 className="student-name">{student.name.toUpperCase()}</h5>
                  <p className="student-course">{student.course}</p>
                </div>
              </div>
              <button 
                onClick={() => deleteStudent(student.id)} 
                className="btn btn-danger btn-sm delete-btn"
              >
                Delete
              </button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default StudentList;
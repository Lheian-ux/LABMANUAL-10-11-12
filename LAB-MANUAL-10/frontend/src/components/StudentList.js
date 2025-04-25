import React from "react";

const StudentList = ({ students, deleteStudent }) => {
  return (
    <div>
      <h2>Student List</h2>
      <ul>
        {students.map((student) => (
          <li key={student.id}>
            {student.name} - {student.course}
            <button onClick={() => deleteStudent(student.id)} className="delete-btn">
              Delete
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default StudentList;
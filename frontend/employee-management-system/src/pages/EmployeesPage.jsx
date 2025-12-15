import { useEffect, useState } from "react";
import { getEmployees, createEmployee, updateEmployee, deleteEmployee } from "../api/employeesApi";
import EmployeeForm from "../components/EmployeeForm";
import EmployeeTable from "../components/EmployeeTable";

export default function EmployeesPage() {
  const [employees, setEmployees] = useState([]);
  const [editing, setEditing] = useState(null);

  // ✅ Fixed: declare loadEmployees inside useEffect
  useEffect(() => {
    async function loadEmployees() {
      const data = await getEmployees();
      setEmployees(data);
    }
    loadEmployees();
  }, []);

  async function handleCreate(values) {
    const created = await createEmployee(values);
    setEmployees([created, ...employees]);
    setEditing(null);
  }

  async function handleUpdate(values) {
    const updated = await updateEmployee(editing.id, values);
    setEmployees(employees.map(e => e.id === updated.id ? updated : e));
    setEditing(null);
  }

  async function handleDelete(emp) {
    await deleteEmployee(emp.id);
    setEmployees(employees.filter(e => e.id !== emp.id));
  }

  return (
    <div className="container py-4">
      <h2>Employee Management</h2>
      {editing ? (
        <EmployeeForm
          initialValues={editing}
          onSubmit={editing.id ? handleUpdate : handleCreate}
          onCancel={() => setEditing(null)}
        />
      ) : (
        <button className="btn btn-primary mb-3" onClick={() => setEditing({})}>
          + Add Employee
        </button>
      )}
      <EmployeeTable employees={employees} onEdit={setEditing} onDelete={handleDelete} />
    </div>
  );
}

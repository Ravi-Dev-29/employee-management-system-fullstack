import { useState, useEffect } from "react";

export default function EmployeeForm({ initialValues, onSubmit, onCancel }) {
  const [values, setValues] = useState({ firstName: "", lastName: "", email: "" });

  useEffect(() => {
    if (initialValues) {
      Promise.resolve().then(() => {
        setValues(prev => ({ ...prev, ...initialValues }));
      });
    }
  }, [initialValues]);

  function handleChange(e) {
    setValues({ ...values, [e.target.name]: e.target.value });
  }

  function handleSubmit(e) {
    e.preventDefault();
    onSubmit(values);
  }

  return (
    <form onSubmit={handleSubmit} className="card card-body mb-3">
      <input
        className="form-control mb-2"
        name="firstName"
        placeholder="First Name"
        value={values.firstName}
        onChange={handleChange}
      />
      <input
        className="form-control mb-2"
        name="lastName"
        placeholder="Last Name"
        value={values.lastName}
        onChange={handleChange}
      />
      <input
        className="form-control mb-2"
        name="email"
        placeholder="Email"
        value={values.email}
        onChange={handleChange}
      />
      <div className="d-flex gap-2">
        <button type="submit" className="btn btn-primary">Save</button>
        {onCancel && (
          <button type="button" className="btn btn-secondary" onClick={onCancel}>
            Cancel
          </button>
        )}
      </div>
    </form>
  );
}

const BASE = "/api/employees"; // proxy handles backend

export async function getEmployees() {
  const res = await fetch(BASE);
  return res.json();
}

export async function createEmployee(payload) {
  const res = await fetch(BASE, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(payload),
  });
  return res.json();
}

export async function updateEmployee(id, payload) {
  const res = await fetch(`${BASE}/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(payload),
  });
  return res.json();
}

export async function deleteEmployee(id) {
  await fetch(`${BASE}/${id}`, { method: "DELETE" });
}

const addUser = () => {
  const addUserForm = document.getElementById("addUserForm").elements;
  const name = addUserForm["name"].value;
  const city = addUserForm["city"].value;
  const state = addUserForm["state"].value;
  const zipcode = addUserForm["zipcode"].value;
  const hasPeanutAllergy = addUserForm["hasPeanutAllergy"].value;
  const hasEggAllergy = addUserForm["hasEggAllergy"].value;
  const hasDairyAllergy = addUserForm["hasDairyAllergy"].value;

  const User = {
    name,
    city,
    state,
    zipcode,
    hasPeanutAllergy,
    hasEggAllergy,
    hasDairyAllergy,
  };

  fetch("/users", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(User),
  })
    .then((res) => res.json())
    .then(handleError)
    .then((newUser) => {
      alert(
        `Successfully added user with id ${newUser.id}: (${JSON.stringify(
          newUser
        )})`
      );

      //add user to table
      const usersTableBody = document.getElementById("usersTableBody");
      const usersRow = document.createElement("tr");
      usersRow.id = `userRowId${newUser.id}`;
      usersRow.innerHTML = `
            <td>${newUser.id}</td>
            <td>${newUser.name}</td>
            <td>${newUser.city}</td>
            <td>${newUser.state}</td>
            <td>${newUser.zipcode}</td>
            <td>${newUser.hasPeanutAllergy}</td>
            <td>${newUser.hasEggAllergy}</td>
            <td>${newUser.hasDairyAllergy}</td>
          `;
      usersTableBody.appendChild(usersRow);
      //add newUser.id to delete option
      const userIdList = document.getElementById("userIdList");
      const deleteIdOption = document.createElement("option");
      deleteIdOption.value = newUser.id;
      deleteIdOption.innerText = newUser.id;
      deleteIdOption.id = `userDeleteId${newUser.id}`;
      userIdList.appendChild(deleteIdOption);
      //add newUser.name to update option
      const updateUserNameList = document.getElementById("updateUserNameList");
      const updateNameOption = document.createElement("option");
      updateNameOption.value = newUser.name;
      updateNameOption.innerText = newUser.name;
      updateNameOption.id = `userName${newUser.id}`;
      updateUserNameList.appendChild(updateNameOption);
    })
    .catch(renderError);
};

const deleteUser = () => {
  const deleteUserForm = document.getElementById("deleteUserForm").elements;
  const userId = deleteUserForm["userToDelete"].value;

  fetch(`/users/${userId}`, {
    method: "DELETE",
  })
    .then(() => {
      alert(`Successfully deleted user with id ${userId}.`);

      //remove deleted user table row
      const deletedUserRow = document.getElementById(`userRowId${userId}`);
      deletedUserRow.remove();
      //remove deleted User id from delete form
      const deletedUserIdOption = document.getElementById(
        `userDeleteId${userId}`
      );
      deletedUserIdOption.remove();
      //remove update Username from update option
      const updateUsernameOption = document.getElementById(`userName${userId}`);
      updateUsernameOption.remove();
    })
    .then(handleError)
    .catch(renderError);
};

const fetchAndRenderAllUsersDetails = () => {
  fetch("/users")
    .then((res) => res.json())
    .then(handleError)
    .then((users) => {
      renderUsersTable(users);
      renderDeleteOption(users);
      renderUpdateOption(users);
    })
    .catch(renderError);
};
const renderUsersTable = (users) => {
  const usersTableBody = document.getElementById("usersTableBody");
  while (usersTableBody.firstChild) {
    usersTableBody.removeChild(usersTableBody.firstChild);
  }
  users.forEach((user) => {
    const usersRow = document.createElement("tr");
    usersRow.id = `userRowId${user.id}`;
    usersRow.innerHTML = `
         <td>${user.id}</td>
         <td>${user.name}</td>
         <td>${user.city}</td>
         <td>${user.state}</td>
         <td>${user.zipcode}</td>
         <td>${user.hasPeanutAllergy}</td>
         <td>${user.hasEggAllergy}</td>
         <td>${user.hasDairyAllergy}</td>
       `;
    usersTableBody.appendChild(usersRow);
  });
};

const renderDeleteOption = (users) => {
  const userIdList = document.getElementById("userIdList");
  users.forEach((user) => {
    const deleteIdOption = document.createElement("option");
    deleteIdOption.value = user.id;
    deleteIdOption.innerText = user.id;
    deleteIdOption.id = `userDeleteId${user.id}`;
    userIdList.appendChild(deleteIdOption);
  });
};
const renderUpdateOption = (users) => {
  const updateUserNameList = document.getElementById("updateUserNameList");
  const emptyOption = document.createElement("option");
  emptyOption.value = "";
  emptyOption.innerText = "";
  updateUserNameList.appendChild(emptyOption);
  users.forEach((user) => {
    const updateNameOption = document.createElement("option");
    updateNameOption.value = user.name;
    updateNameOption.innerText = user.name;
    updateNameOption.id = `userName${user.id}`;
    updateUserNameList.appendChild(updateNameOption);
  });
};
const getUserByName = () => {
  const updateUserNameList = document.getElementById("updateUserNameList");
  const updateUserForm = document.getElementById("updateUserForm").elements;
  const username = updateUserNameList.value;
  if (username == "") {
    updateUserForm["city"].value = "";
    updateUserForm["state"].value = "";
    updateUserForm["zipcode"].value = "";
    updateUserForm["hasPeanutAllergy"].value = "false";
    updateUserForm["hasEggAllergy"].value = "false";
    updateUserForm["hasDairyAllergy"].value = "false";
  } else {
    fetch(`/users/${username}`)
      .then((res) => res.json())
      .then(handleError)
      .then((user) => {
        updateUserForm["city"].value = user.city;
        updateUserForm["state"].value = user.state;
        updateUserForm["zipcode"].value = user.zipcode;
        updateUserForm["hasPeanutAllergy"].value = user.hasPeanutAllergy;
        updateUserForm["hasEggAllergy"].value = user.hasEggAllergy;
        updateUserForm["hasDairyAllergy"].value = user.hasDairyAllergy;
      })
      .catch(renderError);
  }
};

const handleError = (json) => {
  if (json.errorMessage) {
    throw new Error(json.errorMessage);
  }
  return json;
};

const renderError = (message) => {
  alert(message);
};
document.addEventListener("DOMContentLoaded", fetchAndRenderAllUsersDetails);

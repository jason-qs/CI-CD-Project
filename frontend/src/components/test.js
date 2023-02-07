import React from "react";

const Users = ({users}) => {
    return <div>
        <h1>Users</h1>
        {users.map((user) => (
            <div>
                <h2>{user.firstName}</h2>
            </div>
        ))}
    </div>
};

export default Users
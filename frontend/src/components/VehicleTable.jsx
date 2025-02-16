import React from "react";

const VehicleTable = ({ vehicles, onEdit, onDelete , onToggleStatus }) => {
    const vehicleList = Array.isArray(vehicles) ? vehicles : [];

    return (
        <table className="table table-striped table-bordered table-hover">
            <thead className="table-secondary">
                <tr>
                    <th>Vehicle Number</th>
                    <th>Owner</th>
                    <th>Type</th>
                    <th>Fuel Type</th>
                    <th>Fuel Quota</th>
                    <th>Chassis Number</th>
                    <th>Status</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                {vehicleList.length === 0 ? (
                    <tr>
                        <td colSpan="8">No vehicles found</td>
                    </tr>
                ) : (
                    vehicleList.map((vehicle) => (
                        <tr key={vehicle.id}>
                            <td>{vehicle.vehicleNumber}</td>
                            <td>{vehicle.ownerName}</td>
                            <td>{vehicle.vehicleType}</td>
                            <td>{vehicle.fuelType}</td>
                            <td>{vehicle.fuelQuota}</td>
                            <td>{vehicle.chassisNumber}</td>
                            {console.log(vehicle)}
                            <td>
                                <button
                                    className={`btn btn-sm ${vehicle.active ? "btn-success" : "btn-secondary"}`}
                                    onClick={() => onToggleStatus(vehicle.id)}
                                >
                                    {vehicle.active ? "Active" : "Deactive"}
                                </button>
                            </td>

                            <td>
                                <button
                                    className="btn btn-primary btn-sm"
                                    onClick={() => onEdit(vehicle)}
                                >
                                    Edit
                                </button>
                            </td>
                            <td>
                                <button
                                    className="btn btn-danger btn-sm"
                                    onClick={() => onDelete(vehicle.id)}
                                >
                                    Delete
                                </button>
                            </td>
                        </tr>
                    ))
                )}
            </tbody>
        </table>
    );
};

export default VehicleTable;
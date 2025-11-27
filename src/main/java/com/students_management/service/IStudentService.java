package com.students_management.service;

import java.util.List;

/**
 * This interface IStudentService was created to simplify and generalize the behavior of all the DAO (Data Access Object)
 * classes to be implemented in this project. The main idea is to reuse the written code, by applying the DRY and KISS
 * principles.
 * @param <E>
 */
public interface IStudentService<E> {
    // CRUD (Create-Read-Update-Delete) Interface
    /**
     * Recovers all the available registers from a given SQL table on the database.
     * @return A List object, filled with the all the entity/domain objects recovered.
     */
    List<E> searchAll();

    /**
     * Recovers a specific register from the database using its ID.
     * @param studentID
     * @return The customer register if it was already created before on the database. Otherwise, the return value
     * is going to be null.
     */
    E searchByID(Integer studentID);

    /**
     * Single method for both creation and update of registers on the DB. If the object's ID
     * attribute is equal to 'null', then the register is going to be created on the DB with the specific data.
     * However, if the object's ID does exist on the DB, then Spring is going to automatically update the register's
     * information in full.
     * @param studentToSave
     */
    void saveRegister(E studentToSave);

    /**
     * Deletes a register from the database, based on the register's ID obtained through the object received
     * as a parameter.
     * @param studentToDelete
     * @return True or false, whether the register was effectively removed from the database or not.
     */
    void deleteRegister(E studentToDelete);
}

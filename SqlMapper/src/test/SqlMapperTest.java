// Copyright (c) Philipp Wagner. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package de.bytefish.sqlmapper;

import de.bytefish.sqlmapper.result.SqlMappingResult;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.Result;

import java.sql.*;
import java.time.LocalDate;

public class SqlMapperTest extends TransactionalTestBase {

    private class Person {

        private String firstName;

        private String lastName;

        private LocalDate birthDate;

        public Person() {}

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public LocalDate getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
        }
    }

    public class PersonMap extends AbstractMap<Person>
    {
        public PersonMap() {
            super("sample", "unit_test");

            map("first_name", String.class, Person::setFirstName);
            map("last_name", String.class, Person::setLastName);
            map("birth_date", LocalDate.class, Person::setBirthDate);
        }
    }

    @Override
    protected void onSetUpInTransaction() throws Exception {
        createTable();
    }

    @Test
    public void testToEntity() throws Exception {
        SqlMapper<Person> sqlMapper = new SqlMapper<>(() -> new Person(), new PersonMap());

        Person person0 = new Person();

        person0.firstName = "Philipp";
        person0.lastName = "Wagner";
        person0.birthDate = LocalDate.of(1986, 5, 12);

        insert(person0);


        ResultSet resultSet = selectAll();
        while (resultSet.next() ) {

            SqlMappingResult<Person> person = sqlMapper.toEntity(resultSet);

            Assert.assertEquals(true, person.isValid());

            Assert.assertEquals("Philipp", person.getEntity().get().getFirstName());
            Assert.assertEquals("Wagner", person.getEntity().get().getLastName());
            Assert.assertEquals(LocalDate.of(1986, 5, 12), person.getEntity().get().getBirthDate());
        }
    }

    private boolean createTable() throws SQLException {

        String sqlStatement = "CREATE TABLE sample.unit_test\n" +
                "            (\n" +
                "                first_name text,\n" +
                "                last_name text,\n" +
                "                birth_date date\n" +
                "            );";

        Statement statement = connection.createStatement();

        return statement.execute(sqlStatement);
    }

    private ResultSet selectAll() throws SQLException {
        Statement stmt = connection.createStatement();

        return stmt.executeQuery("select * from sample.unit_test");
    }

    private void insert(Person person) throws SQLException {

        PreparedStatement pstmt = null;
        try {
            String query = "insert into sample.unit_test(first_name, last_name, birth_date) values(?, ?, ?)";

            pstmt = connection.prepareStatement(query); // create a statement

            pstmt.setString(1, person.getFirstName());
            pstmt.setString(2, person.getLastName());
            pstmt.setTimestamp(3, Timestamp.valueOf(person.getBirthDate().atStartOfDay()));

            pstmt.execute();
        } finally {
            pstmt.close();
        }
    }


}

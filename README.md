# SqlMapper #

[MIT License]: https://opensource.org/licenses/MIT
[NPoco]: https://github.com/schotime/NPoco/
[Dapper]: https://github.com/StackExchange/dapper-dot-net
[OrmLite]: https://github.com/ServiceStack/ServiceStack.OrmLite
[SqlMapper]: https://github.com/bytefish/SqlMapper


.NET has a large amount of Micro ORMs ([Dapper], [NPoco], [OrmLite], ...) to access databases in a very 
simple way, while Java is lacking small libraries. SqlMapper is a tiny wrapper over the Java ``ResultSet``, 
that makes it very easy to map betweem Java Beans and a database table.

## Setup ##

[SqlMapper] is available in the Central Maven Repository. 

You can add the following dependencies to your pom.xml to include [SqlMapper] in your project.

```xml
<dependency>
	<groupId>de.bytefish</groupId>
	<artifactId>sqlmapper</artifactId>
	<version>0.2</version>
</dependency>
```

## Basic Usage ##

### Database Table ###

Imagine we have the following database table (PostgreSQL):

```
CREATE TABLE sample.unit_test
(
    first_name text,
    last_name text,
    birth_date date
);
```

### Domain Model ###

And we are mapping to the following Java object:

```java
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
```

### Define the Mapping ###

Then all you have to do is define the mapping between both:

```java
public class PersonMap extends AbstractMap<Person>
{
    public PersonMap() {
        super("sample", "unit_test");

        map("first_name", String.class, Person::setFirstName);
        map("last_name", String.class, Person::setLastName);
        map("birth_date", LocalDate.class, Person::setBirthDate);
    }
}
```

### Map ResultSet ###

An ``SqlMapper`` is used to map a SQL ``ResultSet`` to Java objects. The ``SqlMapper.toEntity`` 
method returns a ``SqlMappingResult<TEntity>``, because no exception should be thrown during parsing 
the result set.

#### Stream the ResultSet ####

@Test
public void testToEntityStream() throws Exception {
    // Create a SqlMapper, which maps between a ResultSet row and a Person entity:
    SqlMapper<Person> sqlMapper = new SqlMapper<>(() -> new Person(), new PersonMap());
    
    // Number of persons to insert:
    int numPersons = 10000;
    
    // Insert the given number of persons:
    insert(numPersons);
    
    // Get all row of the Table:
    ResultSet resultSet = selectAll();
        
    // Create the Stream:
    Stream<SqlMappingResult<Person>> stream = sqlMapper.toStream(resultSet);
    
    // Collect the Results as a List:
    List<SqlMappingResult<Person>> result = stream.collect(Collectors.toList());
    
    // Assert the results:
    Assert.assertEquals(numPersons, result.size());
}



#### Iterate the ResultSet ####

Of course it is possible to iterate the ``ResultSet`` and map the rows into Java Objects.

```java
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

        Assert.assertEquals("Philipp", person.getEntity().getFirstName());
        Assert.assertEquals("Wagner", person.getEntity().getLastName());
        Assert.assertEquals(LocalDate.of(1986, 5, 12), person.getEntity().getBirthDate());
    }
}
```
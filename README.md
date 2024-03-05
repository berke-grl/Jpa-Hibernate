<h1>JPA Hibernate Relationships, Fetch Types, and Cascade Types</h1>

This project demonstrates the use of JPA (Java Persistence API) with Hibernate to manage different types of relationships between entities, as well as the use of fetch types and cascade types in a Spring Boot application.

Entity Relationships

<h2>One-to-One</h2>
In a one-to-one relationship, an entity is associated with one and only one other entity.

```java
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private UserProfile profile;
}

@Entity
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "profile")
    private User user;
}
```

<h2>One-to-Many</h2>
In a one-to-many relationship, an entity can be associated with multiple entities.

```java
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> books;
}

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}
```
<h2>Many-to-Many</h2>
In a many-to-many relationship, multiple entities can be associated with multiple other entities.

```java
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_course",
               joinColumns = @JoinColumn(name = "student_id"),
               inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses;
}

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students;
}
```

Fetch Types

EAGER: Entities are fetched eagerly, meaning that related entities are loaded immediately
<br/>
LAZY: Entities are fetched lazily, meaning that related entities are loaded on demand.

```java
@OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
private List<Book> books;
```


<h2>Cascade Types</h2>

ALL: All cascade operations (PERSIST, REMOVE, REFRESH, MERGE, DETACH) are applied.<br/>
PERSIST: The persist operation is cascaded.<br/>
MERGE: The merge operation is cascaded.<br/>
REMOVE: The remove operation is cascaded.<br/>
REFRESH: The refresh operation is cascaded.<br/>
DETACH: The detach operation is cascaded.<br/>

```java
@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "profile_id", referencedColumnName = "id")
private UserProfile profile;
```

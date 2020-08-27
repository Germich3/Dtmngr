
<h3 align="center">Dtmngr</h3>

<div align="center">

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](/LICENSE)
[![GitHub release](https://img.shields.io/github/v/release/Germich3/Dtmngr?color=bightgreen)](https://www.github.com/Germich3/Dtmngr/releases/)

</div>

---

<p align="center"> Provides multiple ways for data processing such as configuration files, object parsing to json, h2 database, csv, etc.
    <br> 
</p>

## Table of Contents

- [Install](#install)
- [Usage](#usage)

## Install <a name="install"></a>

WIP

## Usage <a name="usage"></a>

Exist different managers that can be uses for your propose, some are completely static such as **ConfigurationManager** or **JsonManager**.
Other such as **H2Manager** needs to be initialized.

<ins>**Example JsonManager:**</ins>
```Java
Object obj = JsonManager.load(Path, FileName, Class)

JsonManager.save(Path, FileName, Object)
```

<br>H2Manager can load sql script if the database is new, otherwise it will be ignored.<br>

<ins>**Example H2Manager:**</ins>
```Java
try (H2Manager manager = new H2Manager(Path, Script)) {
    //Some stuff
}
catch (Exception e) {
    //Some stuff
}
```

<br>See more examples <a href="https://github.com/Germich3/Dtmngr/blob/master/src/test/java/es/germich3/ExampleTest.java">here</a>.
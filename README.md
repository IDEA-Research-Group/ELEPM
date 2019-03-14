# ELEPM

ELEPM (Event Log Extractor Process Mining) is an extension of [ELE](https://github.com/IDEA-Research-Group/ELE) 
framework to provide process mining functionalities.
ELE is intended to provide developers and non-expert users with a powerful tool capable of extract event logs in XES 
format to ease process mining tasks. ELEPM provides such process mining facilities by means of some external frameworks
such as ProM or Inductive Miner.



1. [Case study](#case-study)
2. [Running the case study](#runnning-case-study)
3. [Using this framework in your project](#using-external)

<a name="case-study"/>

## Case study

Please refer to [ELE](https://github.com/IDEA-Research-Group/ELE) to check the whole case study. Bellow only the **Test
case C** is shown.

In order to obtain the Petri net, first of all it is necessary to define the extraction as specified in [ELE](https://github.com/IDEA-Research-Group/ELE).

```scala
val xlog = 
 extract(
   define trace id("gticode"),
   define trace event(
     activity = "incidences.incidencetype",
     criteria = orderBy(t"start_date" -> toDate("MM/dd/yyyy HH:mm:ss")),
     timestamp = t"start_date" -> toDate("MM/dd/yyyy HH:mm:ss"),
     resource = t"incidencecode"
   )
 ) from "datasets/aircraft_dataset_anonymized.json"
```

Now, it is possible to obtain the Petri net by using the function `petrinet`. With the methods `save` and `show`, it is
possible to save the Petri net in a text file and show a graph which represents it.

```scala
val petri = petrinet(xlog)

petri save("output/petrinet.txt")
petri show
```




## Running the case study

The implementation has been carried out in a Scala object located at `es.us.idea.ele.XesUtility`. It can be executed by following the following steps:

1. Clone this repository

`https://github.com/IDEA-Research-Group/ELEPM.git`
`cd ADT`

2. Execute the class `es.us.idea.elepm.Utility`

<a name="using-external"/>

## Using this framework in your project

In order to use this framework in a project, you need to import the following Maven dependency:

```XML
<dependency>
    <groupId>es.us.idea</groupId>
    <artifactId>ELEPM</artifactId>
    <version>0.1.0-SNAPSHOT</version>
</dependency>
```

Don't forget to point at our repositories:
```XML
    <repositories>
        <repository>
            <id>release-repo</id>
            <name>Artifactory-releases-local</name>
            <url>http://estigia.lsi.us.es:1681/artifactory/libs-release-local</url>
            <releases><enabled>true</enabled></releases>
            <snapshots><enabled>false</enabled></snapshots>
        </repository>
        <repository>
            <id>snapshot-repo</id>
            <name>Artifactory-snapshots</name>
            <url>http://estigia.lsi.us.es:1681/artifactory/libs-snapshot-local</url>
            <releases><enabled>false</enabled></releases>
            <snapshots><enabled>true</enabled></snapshots>
        </repository>
    </repositories>
```
You will need the following imports to work with this tool:

1. The ELE DSL.
```scala
import es.us.idea.ele.xes.dsl.implicits._
```
2. The functions to perform process mining

```scala
import es.us.idea.elepm.pm.functions._

```

2. The transformation functions from the data-chameleon framework. It enables to transform fields and to specify the criteria to create the event logs..
```scala
import es.us.idea.adt.data.chameleon.internal.dtfs._
```
3. The DSL from the data-chameleon framework. It is usefull to use the transformation functions together with ELE.
```scala
import es.us.idea.adt.data.chameleon.dsl.implicits._
```

# Testing Exercises

This project aims to provide some simple exercises and illustrations of test-driven development using Java, JUnit,
Mockito, JaCoCo and Gradle for building. The master branch that you are most likely reading this from is the final state
of this project, so don't browse around unless you want spoilers...

To follow along with the exercises you should clone this repository and the `git checkout tags/exercise1 -b exercise1`.
This will give you the starting state in it's own branch. Do the exercise and when you're done commit, then you can do
`git checkout tags/answer1 -b answer1` to see what my answer looked like. To proceed on to the next exercise you can
then do `git checkout tags/exercise2 -b exercise2` - if you wish you can also try doing `git merge exercise1` to merge
your version of exercise1 into the starting state, but this may be a bit more work.


## Exercise 1

Question: `git checkout tags/exercise1 -b exercise1`

In this exercise we're going to start building a relatively simple domain model for an application that will assist
users in planning trips to deliver building supplies from warehouses to building sites. The problem is that the most of
the routes are tolled so the algorithm needs to trade-off speed for cost depending on the priorities for a project.

We're going to try following a TDD approach, so we will:
 1. Write a test
 2. Run the test (it will fail - no implementation)
 3. Write a minimal implementation for the test to pass
 4. Run all tests
 5. Refactor
 6. Repeat


### Planning

Let's first plan out our domain.

We'll have the following entities:
 * _Node_: Think of these as points of interest in a transport network (producer, toll gate, build site).
 * _Road_: In our simplistic model a road is a connection that can be used to move between two nodes.
 * _Vehicle_: A vehicle moves between nodes and can pick up materials at a warehouse and drop it at a build site.
 * _Material_: Materials are needed to complete a build and need to be transported from producers to build sites. 

A _Node_ should have:
 * Have a _name_
 * Have a _type_:
    * producer
    * consumer
    * toll
 * List of _vehicles_
 * A _neighbours_ map of connected nodes with values mapped to _Road_ to get details about distance

A _Road_ should have:
 * _distance_ (in km)
 * _speedLimit_ (in km/h)
 * 2 _nodes_

_Material_ can be implemented as an enumeration which should have:
 * _weight_ in metric tons
 * _density_ in cubic meters per metric ton (multiply this by _weight_ to determine the _volume_)  
 * User-friendly _name_ and _type_:
    * bricks (density: 1.89)
    * concrete (density: 2.4)
    * sand (density: 1.6)
    * water (density: 1.0)

_Vehicle_ carries _Material_ - we'll make the simplifying assumption that a vehicle can carry only one type of material
at a time. _Vehicle_ should have:
 * User-friendly _name_
 * _weightLimit_ is the maximum weight that can be carried in metric tons
 * _volumeLimit_ is the maximum volume that can be carried in cubic meters
 * _typeLimit_ is the allowed list of _Material_ types that can be carried
 * _cargoType_ is the currently carried _Material_ (or null)
 * _cargoWeight_ is the currently carried weight of material in metric tons (or null)
 * _maxSpeed_ is the maximum speed in km/h
 * _maxSpeedLoaded_ is the maximum speed at full load in km/h
 * _currentSpeed_ (calculated): current speed taking load into account


### Implementation

#### Material

Material has no dependencies on anything else, so let's start our implementation there... Let's try TDD and start by
adding some failing tests to check if all the materials we need have been created:

```
package za.co.entelect.test;

import org.junit.Assert;
import org.junit.Test;

public class MaterialTest {

    @Test
    public void hasBricks() {
        Material sut = Material.Bricks;
        Assert.assertNotNull(sut);
    }

    @Test
    public void hasConcrete() {
        Material sut = Material.Concrete;
        Assert.assertNotNull(sut);
    }

    @Test
    public void hasSand() {
        Material sut = Material.Sand;
        Assert.assertNotNull(sut);
    }

    @Test
    public void hasWater() {
        Material sut = Material.Water;
        Assert.assertNotNull(sut);
    }
}
```

These are no the most useful tests, but try running them with `gradle test` - compilation obviously fails, so let's fix
that next by creating a basic implementation of _Material_:
```
package za.co.entelect.test;

public enum Material {
    Bricks,
    Concrete,
    Sand,
    Water
}
```

Run your tests again and they should succeed, but you'll see that Gradle isn't printing out the results of the
individual tests. Add the following to `build.gradle` and rerun with `gradle clean test` to get a nice visual display
of your test results:
```
test {
    testLogging {
        events "passed", "skipped", "failed"
    }
}
```

Before continuing let's run `gradle test --continuous` to test Gradle's new continuous build feature (it's in incubation
so it's still under development, but it should work nicely for red-green testing).

Our small planning section also specified that the different materials should have different densities - let's add some
tests for that:
```
    @Test
    public void brickDensityIsCorrect() {
        Material sut = Material.Bricks;

        Assert.assertEquals(1.89, sut.getDensity());
    }

    @Test
    public void concreteDensityIsCorrect() {
        Material sut = Material.Concrete;

        Assert.assertEquals(2.4, sut.getDensity());
    }

    @Test
    public void sandDensityIsCorrect() {
        Material sut = Material.Sand;

        Assert.assertEquals(1.6, sut.getDensity());
    }

    @Test
    public void waterDensityIsCorrect() {
        Material sut = Material.Water;

        Assert.assertEquals(1.0, sut.getDensity());
    }
```

You should now have a project that is failing on compilation again. Let's fix that by adding the missing getDensity
method in the Material enum:
```
public enum Material {
    Bricks,
    Concrete,
    Sand,
    Water;

    public double getDensity() {
        return 0.0;
    }
}
```

Now the project compiles again, but the new tests are still failing. So to finish off this class let's add a
constructor that sets a private density field and return that instead:
```
public enum Material {
    Bricks(1.89),
    Concrete(2.4),
    Sand(1.6),
    Water(1.0);

    private double density;

    Material(double density) {
        this.density = density;
    }

    public double getDensity() {
        return density;
    }
}
```

Tests are still failing?! Well it turns out the version of `Assert.assertEquals` I used is deprecated - I should've used
`Assert.assertEquals(double expected, double actual, double delta)` where delta specifies the margin of error that is
allowed (remember doubles are inaccurate by nature). So update the tests to:
```
    @Test
    public void brickDensityIsCorrect() {
        Material sut = Material.Bricks;

        Assert.assertEquals(1.89, sut.getDensity(), 0.01);
    }

    @Test
    public void concreteDensityIsCorrect() {
        Material sut = Material.Concrete;

        Assert.assertEquals(2.4, sut.getDensity(), 0.01);
    }

    @Test
    public void sandDensityIsCorrect() {
        Material sut = Material.Sand;

        Assert.assertEquals(1.6, sut.getDensity(), 0.01);
    }

    @Test
    public void waterDensityIsCorrect() {
        Material sut = Material.Water;

        Assert.assertEquals(1.0, sut.getDensity(), 0.01);
    }
```

#### Aside: Code Coverage

It can sometimes be useful to get some code coverage reports to make sure that your tests are testing what you think
they are and that you didn't miss anything important. First add the JaCoCo plugin near the top of your `build.gradle`:
```
apply plugin: 'jacoco'
```

Then add some configuration:
```
jacoco {
    toolVersion = "0.7.7.201606060606"
}

jacocoTestReport {
    reports {
        xml.enabled = false
        csv.enabled = false
        html.enabled = true
    }
}

test {
    jacoco {
        append = false
    }
}
```

Finally run `gradle clean test jacocoTestReport` and check `build/reports/jacoco` for an `index.html` file and view
your coverage report.

#### Vehicle

*Exercise:* Implement vehicle as specified with some tests to ensure that it's working correctly (especially focus on the
current speed calculation).

The current speed calculation should be something like `maxSpeed - (maxSpeed - maxSpeedLoaded) * (cargoWeight / weightLimit)`. 


#### Node & Road

*Exercise:* Implement the Node and Road entities. You'll need to get a bit creative here as Node and Road in the
specification have a circular dependency on each other.


### Finished

Well done on getting the basic model built with test coverage! You can check out the `answer1` branch to see what my
solution looks like. Next up we'll change the specification a bit and use the opportunity to introduce mocking.

Answer: `git checkout tags/answer1 -b answer1`
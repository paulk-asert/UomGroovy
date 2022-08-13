/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import groovy.transform.TypeChecked
import org.codehaus.groovy.control.CompilationFailedException
import tech.units.indriya.quantity.Quantities
import tech.units.indriya.quantity.QuantityRange
import tech.units.indriya.quantity.time.TemporalQuantity

import javax.measure.UnconvertibleException

import static groovy.test.GroovyAssert.shouldFail
import static java.time.temporal.ChronoUnit.MILLIS
import static java.time.temporal.ChronoUnit.MINUTES
import static java.time.temporal.ChronoUnit.SECONDS
import static javax.measure.MetricPrefix.KILO
import static systems.uom.unicode.CLDR.ASTRONOMICAL_UNIT
import static systems.uom.unicode.CLDR.CELSIUS
import static systems.uom.unicode.CLDR.FAHRENHEIT
import static systems.uom.unicode.CLDR.KILOMETER
import static systems.uom.unicode.CLDR.LIGHT_YEAR
import static systems.uom.unicode.CLDR.MILE
import static systems.uom.unicode.CLDR.PARSEC
import static tech.units.indriya.unit.Units.GRAM
import static tech.units.indriya.unit.Units.METRE

var distance = Quantities.getQuantity(800, METRE)
var distanceB = Quantities.getQuantity(1.5, KILO(METRE))
var result = distance.add(distanceB)
var kiloDistance = result.to(KILO(METRE))
println result
println kiloDistance
println distanceB.add(distance)
println distanceB.getUnit().getSystemUnit()

var distanceC = Quantities.getQuantity(1000, METRE)
println 'distanceC.unit = ' + distanceC.unit
println distanceC.unit.dimension
println 'distanceC.unit.systemUnit = ' + distanceC.unit.systemUnit
println MILE
println MILE.dimension
println MILE.getSystemUnit()
println MILE.isCompatible(distanceC.unit)
println distanceC.to(MILE)

var weight = Quantities.getQuantity(75, KILO(GRAM))
println distance.unit
println weight.unit
println distance.unit.isCompatible(weight.unit)
def ex = shouldFail(UnconvertibleException) {
    result.add(weight)
}
println ex.message

ex = shouldFail CompilationFailedException, '''
import groovy.transform.TypeChecked
import tech.units.indriya.quantity.Quantities
import static tech.units.indriya.unit.Units.*

@TypeChecked
static void main(args) {
    Quantities.getQuantity(1, METRE).add(Quantities.getQuantity(1, SECOND))
}
'''
println ex.message
def time = TemporalQuantity.of(3, MINUTES).add(TemporalQuantity.of(30, SECONDS)).add(TemporalQuantity.of(12, MILLIS))
println time
var speed = distanceB.divide(time)
println speed
println speed.unit

def minToMars = Quantities.getQuantity(56_000_000, KILO(METRE))
def maxToMars = Quantities.getQuantity(225_000_000, KILO(METRE))
println QuantityRange.of(minToMars, maxToMars)
println minToMars.to(MILE)
println minToMars.to(ASTRONOMICAL_UNIT)
println maxToMars.to(LIGHT_YEAR)
println maxToMars.to(PARSEC)

distToProximaCentauriB = Quantities.getQuantity(4.2, LIGHT_YEAR)
println distToProximaCentauriB
println distToProximaCentauriB.to(PARSEC)

assert Quantities.getQuantity(0, CELSIUS) == Quantities.getQuantity(32, FAHRENHEIT)

var minTemp = Quantities.getQuantity(-128, CELSIUS)
var maxTemp = Quantities.getQuantity(70, FAHRENHEIT)
println minTemp
println minTemp.to(FAHRENHEIT)
println maxTemp
println maxTemp.to(CELSIUS)
println QuantityRange.of(minTemp, maxTemp)

var massₘ = Quantities.getQuantity(6.39E23, KILO(GRAM))
var diameterₘ = Quantities.getQuantity(6_779, KILOMETER)
println massₘ
println diameterₘ
//println massₘ > diameterₘ
println diameterₘ.to(MILE)
assert massₘ.value > diameterₘ.value

@TypeChecked
def method() {
    var massₘ = Quantities.getQuantity(6.39E23, KILO(GRAM))
    println massₘ
    var diameterₘ = Quantities.getQuantity(6_779, KILOMETER)
//    println massₘ.add(diameterₘ)
}

method()

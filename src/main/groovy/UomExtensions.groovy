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
import tech.units.indriya.quantity.Quantities

import javax.measure.Quantity
import javax.measure.quantity.Length
import javax.measure.quantity.Mass
import javax.measure.quantity.Speed
import javax.measure.quantity.Time

import static javax.measure.MetricPrefix.CENTI
import static javax.measure.MetricPrefix.KILO
import static tech.units.indriya.unit.Units.GRAM
import static tech.units.indriya.unit.Units.HOUR
import static tech.units.indriya.unit.Units.METRE
import static tech.units.indriya.unit.Units.SECOND

class UomExtensions {
    static Quantity<Length> getCentimeters(Number num) { Quantities.getQuantity(num, CENTI(METRE)) }

    static Quantity<Length> getMeters(Number num) { Quantities.getQuantity(num, METRE) }

    static Quantity<Length> getKilometers(Number num) { Quantities.getQuantity(num, KILO(METRE)) }

    static Quantity<Length> getCm(Number num) { getCentimeters(num) }

    static Quantity<Length> getM(Number num) { getMeters(num) }

    static Quantity<Length> getKm(Number num) { getKilometers(num) }

    static Quantity<Mass> getKilograms(Number num) { Quantities.getQuantity(num, KILO(GRAM)) }

    static Quantity<Mass> getKgs(Number num) { getKilograms(num) }

    static Quantity<Time> getHours(Number num) { Quantities.getQuantity(num, HOUR) }

    static Quantity<Time> getSeconds(Number num) { Quantities.getQuantity(num, SECOND) }

    static Quantity<Time> getHr(Number num) { getHours(num) }

    static Quantity<Time> getS(Number num) { getSeconds(num) }

    static Quantity<Speed> div(Quantity<Length> q, Quantity<Time> divisor) { q.divide(divisor) as Quantity<Speed> }

    static <Q> Quantity<Q> div(Quantity<Q> q, Number divisor) { q.divide(divisor) }

    static <Q> Quantity<Q> plus(Quantity<Q> q, Quantity<Q> divisor) { q.add(divisor) }

    static <Q> Quantity<Q> minus(Quantity<Q> q, Quantity<Q> divisor) { q.subtract(divisor) }
}

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
import static systems.uom.unicode.CLDR.KILOMETER

def s = 1.s
assert 1000.meters == 1.km && 1.m == 100.cm

var gₘ = 3.7.m/s/s
var gₑ = 9.8.m/s/s
assert gₑ.toString() == '9.8 m/s²'
assert gₑ > gₘ

var diameterₘ = Quantities.getQuantity(6_779, KILOMETER)

var diameterₑ = 12_742.kilometers
assert diameterₘ + diameterₘ > diameterₑ
assert diameterₑ - diameterₘ < diameterₘ
assert diameterₘ * 1.8 < diameterₑ

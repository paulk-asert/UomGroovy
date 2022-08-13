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

import javax.measure.Quantity
import javax.measure.quantity.Length
import javax.measure.quantity.Speed

import static Direction.*

class MoveHolder {
    Direction dir
    ByHolder by(Quantity<Length> dist) {
        new ByHolder(dist: dist, dir: dir)
    }
}

class ByHolder {
    Quantity<Length> dist
    Direction dir
    void at(Quantity<Speed> speed) {
        println "robot moved $dir by $dist at $speed"
    }
}

static MoveHolder move(Direction dir) {
    new MoveHolder(dir: dir)
}

@TypeChecked
static void main(args) {
    def s = 1.s
    move right by 2.m at 5.cm/s
//    move forward by 2.kgs
    moveAgain()
}

@TypeChecked(extensions = ['MarsChecker.groovy'])
static void moveAgain() {
    def s = 1.s
    move right by 2.m at 6.cm/s
}

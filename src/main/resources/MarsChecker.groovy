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

afterMethodCall { call ->
    def method = getTargetMethod(call)
    if (method.name != 'at') return
    if (call.arguments.size() != 1) return
    def arg = call.arguments[0]
    if (arg !instanceof BinaryExpression) return
    def left = arg.leftExpression
    if (left !instanceof PropertyExpression) return
    def obj = left.objectExpression
    if (obj !instanceof ConstantExpression) return
    if (obj.value > 5) {
        addStaticTypeError("Speed of $obj.value is too fast!",call)
        handled = true
    }
}

/*
 * Copyright 2017 Software Engineering and Synthesis Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sesygroup.choreography.abstractparticipantbehavior.model.action;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.sesygroup.choreography.abstractparticipantbehavior.model.State;
import com.sesygroup.choreography.abstractparticipantbehavior.model.action.InternalActionTransition;

/**
 *
 * @author Alexander Perucci (http://www.alexanderperucci.com/)
 *
 */
public class InternalActionTransitionTest {
   private static InternalActionTransition mockedInternalAction;
   private static InternalActionTransition internalAction;

   @BeforeClass
   public static void setUp() {
      State sourceState = new State("source");
      State targetState = new State("target");
      internalAction = new InternalActionTransition(sourceState, targetState);
      mockedInternalAction = Mockito.mock(InternalActionTransition.class);
      Mockito.when(mockedInternalAction.getSourceState()).thenReturn(sourceState);
      Mockito.when(mockedInternalAction.getTargetState()).thenReturn(targetState);
      Mockito.when(mockedInternalAction.toString()).thenReturn("(source, epsilon, target)");
   }

   @Test
   public void testGetSourceState() {
      Assert.assertEquals(mockedInternalAction.getSourceState(), internalAction.getSourceState());
   }

   @Test
   public void testGetTargetState() {
      Assert.assertEquals(mockedInternalAction.getTargetState(), internalAction.getTargetState());
   }

   @Test
   public void testToString() {
      Assert.assertEquals(mockedInternalAction.toString(), internalAction.toString());
   }
}

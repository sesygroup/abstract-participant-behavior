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
package com.sesygroup.choreography.abstractparticipantbehavior.model;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.collections.Sets;

import com.sesygroup.choreography.abstractparticipantbehavior.model.AbstractParticipantBehavior;
import com.sesygroup.choreography.abstractparticipantbehavior.model.State;
import com.sesygroup.choreography.abstractparticipantbehavior.model.action.SendActionTransition;
import com.sesygroup.choreography.abstractparticipantbehavior.model.message.OutputMessage;

/**
 *
 * @author Alexander Perucci (http://www.alexanderperucci.com/)
 *
 */
public class AbstractParticipantBehaviorTest {
   private static AbstractParticipantBehavior mockedAbstractParticipantBehavior;
   private static AbstractParticipantBehavior abstractParticipantBehavior;

   @BeforeClass
   public static void setUp() {
      State initialState = new State("initialState");
      State targetState = new State("targetState");
      OutputMessage outputMessage = new OutputMessage("outputMessage");
      SendActionTransition transition = new SendActionTransition(initialState, targetState, outputMessage);
      abstractParticipantBehavior = new AbstractParticipantBehavior();
      abstractParticipantBehavior.getStates().add(initialState);
      abstractParticipantBehavior.getStates().add(targetState);
      abstractParticipantBehavior.setInitialState(initialState);
      abstractParticipantBehavior.getMessages().add(outputMessage);
      abstractParticipantBehavior.getTransitions().add(transition);

      mockedAbstractParticipantBehavior = Mockito.mock(AbstractParticipantBehavior.class);
      Mockito.when(mockedAbstractParticipantBehavior.getStates()).thenReturn(Sets.newSet(initialState, targetState));
      Mockito.when(mockedAbstractParticipantBehavior.getInitialState()).thenReturn(initialState);
      Mockito.when(mockedAbstractParticipantBehavior.getMessages()).thenReturn(Sets.newSet(outputMessage));
      Mockito.when(mockedAbstractParticipantBehavior.getTransitions()).thenReturn(Sets.newSet(transition));
      Mockito.when(mockedAbstractParticipantBehavior.validate()).thenReturn(true);
   }

   @Test
   public void testGetStates() {
      Assert.assertEquals(mockedAbstractParticipantBehavior.getStates(), abstractParticipantBehavior.getStates());
   }

   @Test
   public void testGetInitialState() {
      Assert.assertEquals(mockedAbstractParticipantBehavior.getInitialState(),
            abstractParticipantBehavior.getInitialState());
   }

   @Test
   public void testGetMessages() {
      Assert.assertEquals(mockedAbstractParticipantBehavior.getMessages(), abstractParticipantBehavior.getMessages());
   }

   @Test
   public void testGetTransitions() {
      Assert.assertEquals(mockedAbstractParticipantBehavior.getTransitions(),
            abstractParticipantBehavior.getTransitions());
   }

   @Test
   public void testValidate() {
      Assert.assertEquals(mockedAbstractParticipantBehavior.validate(), abstractParticipantBehavior.validate());
   }

   @Test()
   public void testNotValidate() {
      AbstractParticipantBehavior abstractParticipantBehavior = new AbstractParticipantBehavior();
      abstractParticipantBehavior.getTransitions().add(new SendActionTransition(new State("initialState"),
            new State("targetState"), new OutputMessage("outputMessage")));
      Assert.assertFalse(abstractParticipantBehavior.validate());
   }

   @Test()
   public void testValidateEmptyAbstractParticipantBehavior() {
      Assert.assertTrue(new AbstractParticipantBehavior().validate());

   }

}

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
package com.sesygroup.choreography.abstractparticipantbehavior.generator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.sesygroup.choreography.choreographyspecification.model.ChoreographySpecification;
import com.sesygroup.choreography.choreographyspecification.model.Participant;
import com.sesygroup.choreography.abstractparticipantbehavior.model.AbstractParticipantBehavior;
import com.sesygroup.choreography.abstractparticipantbehavior.model.State;
import com.sesygroup.choreography.abstractparticipantbehavior.model.action.ReceiveActionTransition;
import com.sesygroup.choreography.abstractparticipantbehavior.model.action.SendActionTransition;

/**
 *
 * @author Alexander Perucci (http://www.alexanderperucci.com/)
 *
 */
public class ChoreographyProjection {

   public ChoreographyProjection() {

   }

   public Map<Participant, AbstractParticipantBehavior> projectAll(
         final ChoreographySpecification choreographySpecification) {

      Map<Participant, AbstractParticipantBehavior> participantToAbstractParticipantBehaviorMap
            = new HashMap<Participant, AbstractParticipantBehavior>();
      choreographySpecification.getParticipants().forEach(participant -> participantToAbstractParticipantBehaviorMap
            .put(participant, project(choreographySpecification, participant)));

      return participantToAbstractParticipantBehaviorMap;
   }

   public AbstractParticipantBehavior project(final ChoreographySpecification choreographySpecification,
         final Participant participant) {
      AbstractParticipantBehavior abstractParticipantBehavior = new AbstractParticipantBehavior();
      // set all states
      choreographySpecification.getStates()
            .forEach(state -> abstractParticipantBehavior.getStates().add(new State(state.getName())));
      // set initial state
      abstractParticipantBehavior.setInitialState(new State(choreographySpecification.getInitialState().getName()));
      // add internal action transitions
      abstractParticipantBehavior.getTransitions()
            .addAll(ChoreographyProjectionUtils.findInternalActionTransitions(choreographySpecification, participant));
      // add receive action transitions
      Collection<ReceiveActionTransition> receiveActionTransitions
            = ChoreographyProjectionUtils.findReceiveActionTransitions(choreographySpecification, participant);
      receiveActionTransitions.stream().map(ReceiveActionTransition::getInputMessage).forEach(message -> {
         if (!abstractParticipantBehavior.getMessages().stream().collect(Collectors.toList()).contains(message)) {
            abstractParticipantBehavior.getMessages().add(message);
         }
      });
      abstractParticipantBehavior.getTransitions().addAll(receiveActionTransitions);
      // add sending action transitions
      Collection<SendActionTransition> sendActionTransitions
            = ChoreographyProjectionUtils.findSendActionTransitions(choreographySpecification, participant);
      sendActionTransitions.stream().map(SendActionTransition::getOutputMessage).forEach(message -> {
         if (!abstractParticipantBehavior.getMessages().stream().collect(Collectors.toList()).contains(message)) {
            abstractParticipantBehavior.getMessages().add(message);
         }
      });
      abstractParticipantBehavior.getTransitions().addAll(sendActionTransitions);

      return abstractParticipantBehavior;
   }

}

package callofduty.core;

import callofduty.domain.missions.EscortMission;
import callofduty.domain.missions.HuntMission;
import callofduty.domain.missions.SurveillanceMission;
import callofduty.interfaces.Mission;
import callofduty.interfaces.MissionControl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class MissionControlImplTest {

   private MissionControl missionControl;

   private Mission mission1;
   private Mission mission2;
   private Mission mission3;
   private String idMax;
   private String idMax1;
   private String idMax2;
   private Double raiting;
   private Double raiting1;
   private Double raiting2;
   private Double bounty;
   private Double bounty1;
   private Double bounty2;

    @Before
    public void setUp() throws Exception {

        this.missionControl = new MissionControlImpl();

        this.mission1 = Mockito.mock(EscortMission.class);
        this.mission2 = Mockito.mock(HuntMission.class);
        this.mission3 = Mockito.mock(SurveillanceMission.class);

        this.idMax = "123456789";
        this.idMax1 = "12345678";
        this.idMax2 = "1234567";
        this.raiting = 1000D;
        this.raiting2 = 10D;
        this.bounty = 1000000000D;
        this.bounty2 = 1000D;
        this.raiting1 = -12D;
        this.bounty1 = -12d;

    }

    @Test
    public void generateMissionWithIdMax() {


       Mission mission =  this.missionControl.generateMission(idMax,raiting,bounty);

        Assert.assertEquals(mission.getId(),"12345678");
    }
    @Test
    public void generateMissionWithRaitingMax() {

        Mission mission =  this.missionControl.generateMission(idMax,raiting,bounty);

        Double raiting = 75.0;
        Assert.assertEquals(mission.getRating(),raiting);
    }
    @Test
    public void generateMissionWithBountyMax() {

        Mission mission =  this.missionControl.generateMission(idMax,raiting,bounty);

        Double raiting = 125000.0;
        Assert.assertEquals(mission.getBounty(),raiting);
    }
    @Test
    public void generateMissionWithRaitingMin() {

        Mission mission =  this.missionControl.generateMission(idMax1,raiting1,bounty1);

        Double raiting = 0.0;
        Assert.assertEquals(mission.getRating(),raiting);
    }
    @Test
    public void generateMissionWithBountyMin() {

        Mission mission =  this.missionControl.generateMission(idMax1,raiting1,bounty1);

        Double raiting = 0.0;
        Assert.assertEquals(mission.getBounty(),raiting);
    }
    @Test
    public void generateMissionWithNormalId() {

        Mission mission =  this.missionControl.generateMission(idMax1,raiting1,bounty1);

        String id = "12345678";
        Assert.assertEquals(mission.getId(),id);
    }
    @Test
    public void generateMissionOrder() {


        Mission mission1 =  this.missionControl.generateMission(idMax1,raiting1,bounty1);
        Mission mission =  this.missionControl.generateMission(idMax,raiting,bounty);
        Mission mission2 =  this.missionControl.generateMission(idMax2,raiting2,bounty2);



        Assert.assertEquals(mission1.getClass().getSimpleName(),"EscortMission");
        Assert.assertEquals(mission.getClass().getSimpleName(),"HuntMission");
        Assert.assertEquals(mission2.getClass().getSimpleName(),"SurveillanceMission");
    }


}
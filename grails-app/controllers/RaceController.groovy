import com.racetrack.bean.RaceBean
import com.racetrack.bean.RegistrationBean

class RaceController {
  def scaffold = true
  
  def search = { 
      flash.message = "Search results for: ${params.q}" 
      def resultsMap = Race.search(params.q, params) 
      render(view:'list', 
             model:[ 
               raceInstanceList:resultsMap.results,  
               raceInstanceTotal:Race.countHits(params.q) 
             ] 
      ) 
  }


    def generateReport(){
        List races = create()
//        List races = Race.list()
        println races
        chain(controller:'jasper',action:'index',model:[data:races],params:params)
    }

    public static List create() {
        RaceBean raceBean = new RaceBean();
        raceBean.setName("F1Race");
        raceBean.setCity("Sidney");

        RegistrationBean registrationBean = new RegistrationBean();
        registrationBean.setPaid("Yes");
        RegistrationBean registrationBean2 = new RegistrationBean();
        registrationBean2.setPaid("No!");
        raceBean.setRegistrations(Arrays.asList(registrationBean, registrationBean2));

        RaceBean raceBean2 = new RaceBean();
        raceBean.setName("F1Race");
        raceBean.setCity("Sidney");

        RegistrationBean registrationBean3 = new RegistrationBean();
        registrationBean.setPaid("Yes");
        raceBean.setRegistrations(Arrays.asList(registrationBean3));

        return Arrays.asList(raceBean);
    }

}

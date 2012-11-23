import com.racetrack.bean.RaceBean
import com.racetrack.bean.RegistrationBean
import net.sf.jasperreports.engine.JasperFillManager
import net.sf.jasperreports.engine.JREmptyDataSource
import com.racetrack.bean.SectionBean
import com.racetrack.bean.RequirementBean
import com.racetrack.bean.ProposalBean
import com.racetrack.bean.RootBean

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
        def root = createTree()
//        println sections
//        List races = create()
        params.SUBREPORT_DIR = servletContext.getRealPath('/reports') + "/"
        chain(controller:'jasper',action:'index',model:[data:Arrays.asList(root)],params:params)
    }


    public createTree(){
        RootBean root = new RootBean()
        List<ProposalBean> proposal = new ArrayList<ProposalBean>()
        SectionBean section1 = new SectionBean(name: "Section1", type: "section")
        proposal.add(section1)

        RequirementBean req11 = new RequirementBean(name: "Req11", type: "requirement")
        proposal.add(req11)

        SectionBean section2 = new SectionBean(name: "Section2", type: "section")
        proposal.add(section2)

        root.setEntries(proposal)
        root
    }

    public static List create() {
        RaceBean raceBean = new RaceBean();
        raceBean.setName("F1Race");
        raceBean.setCity("Sidney");


//        raceBean.setRegistrations(Arrays.asList(registrationBean2));
//        raceBean.setRegistrations(Arrays.asList(registrationBean));

        RaceBean raceBean2 = new RaceBean();
        raceBean2.setName("F1Race2");
        raceBean2.setCity("Sidney2");

        RaceBean raceBean3 = new RaceBean();
        raceBean3.setName("F1Race3");
        raceBean3.setCity("Sidney3");

        RegistrationBean registrationBean = new RegistrationBean();
        registrationBean.setPaid("Yes");
        RegistrationBean registrationBean2 = new RegistrationBean();
        registrationBean2.setPaid("No!");
        raceBean.setRegistrations(Arrays.asList(registrationBean, registrationBean2));
        raceBean2.setRegistrations(Arrays.asList(registrationBean));
        raceBean3.setRegistrations(Arrays.asList(registrationBean, registrationBean2));

        println raceBean.registrations

//        return Arrays.asList(raceBean, raceBean2, raceBean3);
        return Arrays.asList(raceBean3);
    }

}

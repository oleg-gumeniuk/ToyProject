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
        def list  = Race.list()
        println list
        chain(controller:'jasper',action:'index',model:[data:list],params:params)
    }

}

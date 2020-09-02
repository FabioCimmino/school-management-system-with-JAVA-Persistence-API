# Assignment3 
### Autore: Fabio Cimmino


Per eseguire questo progetto bisogna creare un database MySQL denominato "**mydb**" 
sulla porta localhost:3306. La verisione di MySQL Server è la 5.7.
Nel file **persistence.xml** dovranno essere cambiati i valori di *user* e *password* in base
alla propria configurazione.

Per importare il progetto seguire i seguenti passi:
1. Scaricare il progetto dal repository gitLab
2. Aprire Eclipse
3. Selezionare **File -> import -> Existing Maven Projects**
4. Selezionare il progetto scaricato
5. Dopo aver importato il progetto, assicurarsi che l'impostazione **Compiler compilance level** è settata a 1.6
   (**Project -> Properties -> Java Compiler -> Compiler compliance level**)

Nel caso in cui non si riesca ad eseguire i test del progetto per il seguente errore "*java compiler level does not match the version of the installed java project facet*",
seguire i seguenti passi:
- Project -> Properties -> Project Facets
- Settare la versione di "*Java*" a 1.6


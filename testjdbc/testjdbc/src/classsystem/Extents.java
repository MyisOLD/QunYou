package classsystem;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class Extents {
    public static void clearConsole() throws AWTException {
        Robot r = new Robot();
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_R);
        r.keyRelease(KeyEvent.VK_R);
        r.keyRelease(KeyEvent.VK_CONTROL);
        r.delay(50);
    }

    public static void goToTableAdd(String waitTableName)throws AWTException{
            if(Objects.equals(waitTableName, "researchlab")){
                clearConsole();
                ResearchLab.LabAdd();
            }
            else if(Objects.equals(waitTableName, "researchproject")){
                clearConsole();
                ResearchProject.ProjectAdd();
            }
            else if(Objects.equals(waitTableName, "researchachievement")){
                clearConsole();
                ResearchAchievement.AchievementAdd();
            }
            else if(Objects.equals(waitTableName, "researcher")){
                clearConsole();
                Researcher.researcherAdd();
            }
            else if(Objects.equals(waitTableName,"OfficeLocation")){
                clearConsole();
                OfficeLocation.OfficeLocationAdd();
            }
            else if(Objects.equals(waitTableName,"Client")){
                clearConsole();
                Client.ClientAdd();
            }
            else if(Objects.equals(waitTableName,"Collaborator")){
                clearConsole();
                Collaborator.CollaboratorAdd();
            }
            else if(Objects.equals(waitTableName,"QualityMonitor")){
                clearConsole();
                QualityMonitor.QualityMonitorAdd();
            }
            else if(Objects.equals(waitTableName,"Director")){
                clearConsole();
                Director.DirectorAdd();
            }else if (Objects.equals(waitTableName, "Secretary")) {
                clearConsole();
                Secretary.SecretaryAdd();
            }
    }

    public static void goToTableDelete(String waitTableName)throws AWTException {
        if (Objects.equals(waitTableName, "researchlab")) {
            clearConsole();
            ResearchLab.deleteLab();
        } else if (Objects.equals(waitTableName, "researchproject")) {
            clearConsole();
            ResearchProject.deleteProject();
        } else if (Objects.equals(waitTableName, "researchachievement")) {
            clearConsole();
            ResearchAchievement.deleteAchievement();
        } else if (Objects.equals(waitTableName, "researcher")) {
            clearConsole();
            Researcher.deleteResearcher();
        } else if (Objects.equals(waitTableName, "OfficeLocation")) {
            clearConsole();
            OfficeLocation.deleteOfficeLocation();
        } else if (Objects.equals(waitTableName, "Client")) {
            clearConsole();
            Client.deleteClient();
        } else if (Objects.equals(waitTableName, "Collaborator")) {
            clearConsole();
            Collaborator.deleteCollaborator();
        } else if (Objects.equals(waitTableName, "QualityMonitor")) {
            clearConsole();
            QualityMonitor.deleteQualityMonitor();
        } else if (Objects.equals(waitTableName, "Director")) {
            clearConsole();
            Director.deleteDirector();
        } else if (Objects.equals(waitTableName, "Secretary")) {
            clearConsole();
            Secretary.deleteSecretary();
        }

    }

    public static void goToTableEdit(String waitTableName)throws AWTException{
        if(Objects.equals(waitTableName, "researchlab")){
            clearConsole();
            ResearchLab.updateLab();
        }
        else if(Objects.equals(waitTableName, "researchproject")){
            clearConsole();
            ResearchProject.updateProject();
        }
        else if(Objects.equals(waitTableName, "researchachievement")){
            clearConsole();
            ResearchAchievement.updateAchievement();
        }
        else if(Objects.equals(waitTableName, "researcher")){
            clearConsole();
            Researcher.updateResearcher();
        }
        else if(Objects.equals(waitTableName,"OfficeLocation")){
            clearConsole();
            OfficeLocation.updateLocation();
        }
        else if(Objects.equals(waitTableName,"Client")){
            clearConsole();
            Client.updateClient();
        }
        else if(Objects.equals(waitTableName,"Collaborator")){
            clearConsole();
            Collaborator.updateCollaborator();
        }
        else if(Objects.equals(waitTableName,"QualityMonitor")){
            clearConsole();
            QualityMonitor.updateQualityMonitor();
        }
        else if(Objects.equals(waitTableName,"Director")){
            clearConsole();
            Director.updateDirector();
        }else if (Objects.equals(waitTableName, "Secretary")) {
            clearConsole();
            Secretary.updateSecretary();
        }
    }


}




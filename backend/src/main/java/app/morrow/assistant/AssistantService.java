package app.morrow.assistant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
@Service @Transactional
public class AssistantService {
 private final AssistantMessageRepository repository; private static final List<String> CRISIS_KEYWORDS=Arrays.asList("자살","자해","죽고","끝내고");
 private static final List<String> MEDICAL_KEYWORDS=Arrays.asList("진단","약","처방","치료","병원","의사");
 public AssistantService(AssistantMessageRepository repository){this.repository=repository;}
 public AssistantMessage sendMessage(String userId,String content){var userMessage=repository.save(new AssistantMessage(userId,AssistantMessage.Role.USER,content,true));var response=generateResponse(content);var assistantMessage=repository.save(new AssistantMessage(userId,AssistantMessage.Role.ASSISTANT,response,true));return assistantMessage;}
 public List<AssistantMessage> getHistory(String userId,OffsetDateTime after){return repository.findByUserIdAndCreatedAtAfterOrderByCreatedAtAsc(userId,after);}
 private String generateResponse(String userContent){if(containsCrisisKeywords(userContent))return "어려운 상황이시군요. 지금 당장 전문적인 도움이 필요합니다.\n\n- 정신건강 위기상담: 1577-0199 (24시간)\n- 생명의 전화: 1588-9191\n- 희망의 전화: 129 (보건복지콜센터)\n\n주변의 믿을 수 있는 사람에게 연락하시거나 가까운 병원 응급실을 방문해 주세요.";if(containsMedicalKeywords(userContent))return "죄송합니다. 이 앱은 의료 진단이나 치료를 제공하지 않습니다. 증상이 지속되거나 걱정되는 경우 반드시 의료 전문가와 상담하시기 바랍니다.";return "안녕하세요. 일상 웰니스를 지원하는 Morrow입니다. 오늘 기분은 어떠신가요? 체크인을 통해 상태를 기록하고 개인화된 추천을 받아보세요.";}
 private boolean containsCrisisKeywords(String content){return CRISIS_KEYWORDS.stream().anyMatch(content::contains);}
 private boolean containsMedicalKeywords(String content){return MEDICAL_KEYWORDS.stream().anyMatch(content::contains);}
}

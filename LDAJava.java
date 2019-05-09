import java.util.List;

import com.github.chen0040.data.utils.TupleTwo;
import com.github.chen0040.lda.Doc;
import com.github.chen0040.lda.Lda;
import com.github.chen0040.lda.LdaResult;


public class LDAJava {
	


	public static void main(String[] args)  {
		
		
		/*
		List<String> docs = Arrays.asList(
				"به گزارش خبرنگار اعزامي خبرگزاري دانشجويان ايران (ايسنا)، دكتر حميد عباچي، با اشاره به اينكه اين مركز اسفند ماه سال گذشته در رشته مهندسي برق و سيستم‌هاي كامپيوتر با پذيرش 30 دانشجو اولين دوره آموزشي خود را در كيش آغاز كرده است، افزود: دانشجويان دوره چهار ساله اين رشته را در گرايشهاي برق نرم‌افزار و سخت افزار، كنترل، ‌كامپيوتر و مهندسي پزشكي به صورت دو دوره دو ساله در دانشگاه كيش و موناش استراليا سپري كرده و مدرك رسمي دانشگاه مذكور را دريافت مي‌كنند. وي سقف پذيرش دانشجو در اين رشته را 200 نفر اعلام و پيش بيني كرد كه تعداد دانشجويان اين دانشگاه از 30 نفر فراتر رفته و رشته‌هاي تحصيلي اين مركز نيز به مهندسي مواد، پتروشيمي، راه و ساختمان، مكانيك، صنايع افزايش يابد. دكتر عباچي افزود: دانشگاه موناش استراليا با 40 سال سابقه تحصيل داراي 50 هزار دانشجو در شش زير گروه بوده و داراي 3 زير مجموعه در مالزي، افريقاي جنوبي و كيش است. وي شرايط تحصيلي و آموزش اين دوره را كاملا همسان، همزمان و تحت نظر دانشگاه موناش استراليا خواند و اظهار كرد: دانشجويان به جاي پرداخت سالانه 12 هزار دلار شهريه تحصيل در دانشگاه موناش استراليا مي‌توانند دو سال تحصيل خود را با پرداخت سالانه شش هزاردلار امريكا در دانشگاه كيش سپري كنند. به علاوه هم اكنون اساتيد اين رشته از دانشگاه صنعتي شريف تامين شده و در اين زمينه تبادل با اساتيد ساير دانشگاه‌هاي كشور صورت گرفته است. استاد دانشگاه موناش استراليا با اعلام آغاز مرحله دوم پذيرش دانشجوي اين رشته در پايان تير ماه جاري، گفت: دانشجويان و كساني كه در كنكور سراسري پذيرفته نشده اند، پس از اعلام فراخوان دانشگاه كيش بعد از كنكور سراسري به شرط داشتن مدرك تحصيلي رشته رياضي، معدل 15 به بالا و نمره 12 به بالا نمره 6 از 9 در آزمون ieltf كه به طور رسمي توسط شوراي فرهنگي بريتانيا در تهران برگزار مي‌شود در اين رشته پذيرفته مي‌شوند. همچنين حسين زمان، مدير اجراي دوره بين المللي مشترك دانشگاه كيش و موناش استراليا با اعلام اينكه اولين مركز آموزش عالي فعاليت بين‌المللي رسمي خود را در كشور از اسفند ماه سال گذشته آغاز كرده است، گفت: ‌بيشتر دانشجويان علاقمند به تحصيل در خارج از كشور پس از ديپلم بلافاصله به ساير كشورها مراجعه كرده و معمولا با مشكلات بسياري نظير ناتواني و عدم تسلط به زبان انگليسي مواجه مي‌شوند، بر اين اساس برقراري دوره‌هاي مشترك و فني جهت تقويت پايه آموزش عالي در داخل كشور بوده و راه چند ساله روشهاي آموزشي را در مدتي كوتاه سپري خواهد كرد. نماينده دانشگاه موناش استراليا در كيش در پايان گفت: دور اول پذيرش دانشجو در اين رشته از بين 300 متقاضي صورت گرفته و از اين بين 30 نفر انتخاب شدند." ,
				"به گزارش سرويس صنفي آموزشي خبرگزاري دانشجويان ايران (ايسنا)، در اين برنامه كه به منظور تاسيس و انتخابات جامعه دانش‌آموختگان سوره برگزار مي‌شود جمعي از مسئولان و مديران سازمان تبليغات اسلامي، مديران حوزه هنري، مديران موسسه آموزش عالي سوره و همچنين جمعي از هنرمندان، استادان و اعضاي هيات علمي اين موسسه حضور دارند. همچنين در اين برنامه از جمعي از دانش آموختگان نمونه و استادان پيشكسوت اين موسسه تقدير مي‌شود. اجراي برنامه‌هاي موسيقي، تئاتر و نمايشگاهي از فعاليت‌هاي دانشجويان در زمينه‌هاي فرهنگي و هنري در گذشته و حال و همچنين غرفه‌هاي عكس يادگاري و ذكر خاطره‌اي از درگذشتگان سوره از جمله برنامه‌هاي جنبي اين گردهمايي مي‌باشد.",
				"نتايج آزمون دوره‌هاي فراگير مقاطع كارشناسي و كارشناسي ارشد دانشگاه پيام‌نور اعلام شد. به گزارش سرويس آموزشي خبرگزاري دانشجويان ايران (ايسنا)، اسامي پذيرفته‌شدگان آزمون نوبت چهارم دوره‌هاي فراگير در مقطع كارشناسي و نوبت سوم در مقطع كارشناسي ارشد دانشگاه پيام‌نور از طريق سايت دانشگاه به آدرس www.pnu.ac.ir اعلام شد. همچنين نتايج مشروطين اسفند ماه 82 كه درس مشروطي خود را در آزمون مرداد ماه 83 امتحان داده‌اند، متعاقبا اعلام خواهد شد. دانشگاه پيام نور همچنين با صدور اطلاعيه‌اي اعلام كرد: دانشگاه هيچ مجوزي براي هيچ موسسه‌اي اعم از دولتي،‌خصوصي، فرهنسگراها به منظور ثبت نام، تشكيل كلاس‌هاي رفع اشكال گروهي، حل مسائل و غيره صادر نكرده است و چنين موسساتي با اين دانشگاه ارتباطي ندارد." ,
				"محمدتقي علوي يزدي، مجري اين طرح پژوهشي در اين‌باره به خبرنگار آموزشي ايسنا، گفت: نتايج به دست آمده نشان مي‌دهد كه اولا فرضيه اصلي براي ‌?? درصد جامعه مورد بررسي مورد تاييد است و مهمترين عوامل موفقيت، وضعيت اقتصادي خوب خانواده، تحصيلات بالاي والدين بوده و مخصوصا مادران فرهنگي و شاغل توانسته‌اند موفقترين افراد را به جامعه اسلامي تحويل دهند. وي تصريح كرد: در مورد ‌?? درصد افراد ناموفق مشخص گرديد كه شرط موفقيت تحصيلي و اجتماعي، تنها داشتن استعداد يادگيري نيست بلكه دو عامل بازدارنده بيروين و دروني مي تواند استعدادها را ضايع نمايند، وجود عوامل بازدارنده بيروني يعني ضعف اقتصادي، جو نامناسب عاطفي خانواده و موقعيت بد جغرافيايي باعث شده است كه تعدادي از معدل بيست‌ها حتي نتوانند مقطع ابتدايي را به پايان برسانند. وي، عدم استعداد يادگيري در سطح عالي، عدم علاقه به ادامه تحصيل براي كسب مدارج علمي را از جمله عوامل بازدارنده دروني عنوان كرد و گفت: تاكيد ما بر معدل بيست‌هايي بوده است كه با عدم استعداد يادگيري در سطح عالي بنا به دلايل معرفي شده با اخذ نمرات ‌?? غير واقعي به گروه معدل بيست‌ها پيوسته‌اند كه در طول ‌?? سال گذشته بر تعداد اين معدل بيست‌ها به طور بي‌رويه‌اي اضافه شده است و اين در حالي است كه در يكي از شهرهاي تابعه استان يزد درصد اين افزايش حدود ‌??/?? درصد بوده است. اين محقق تاكيد دارد: مطالعه زندگي معدل بيست‌هاي ناموفق نشان مي دهد كه نه تنها از زندگي، تحصيل و اجتماعي خود احساس رضايت ندارند بلكه در مواردي طرز تفكر آنان شبيه به افراد ناسازگار است، به همين دليل به والدين دانش آموزان مقطع ابتدايي، آموزگاران دبستاني، مشاوران مدارس راهنمايي، دبيران مقطع متوسطه و بالاخره مسوولين آموزش و پرورش پيشنهاد مي شود تا زماني كه تست‌هاي استاندار جهت تشخيص واقعي استعدادها در مدارس وجود ندارد، منطقي‌ترين روش ارزشيابي آن است كه به دانش آموزان خود نمرات واقعي بدهند. علوي يزدي در پايان تصريح كرد: اين پيشنهاد باعث مي‌گردد تا جوانان جامعه جوان ما‌، واقع گراتر رشد نموده و يك تصوير ذهني واقعي از خود داشته باشند و بديهي است كه انسان‌هاي واقع‌گرا از زندگي واقعي خود بيشتر احسا رضايت مي نمايند."
								
				);
		*/
		
		ReadPara rp = new ReadPara();
		List<String> docs = rp.ReadFiles("resource/persica_mini.txt");
		
		//String sx = rp.RemoveStopWord("زمانی برای بعد از استراحت");
		
		
	
		Lda method = new Lda();
		method.setTopicCount(3);
		method.setMaxVocabularySize(20000);
		//method.setStemmerEnabled(true);
		//method.setRemoveNumbers(true);
		//method.setRemoveXmlTag(true);
		//method.addStopWords(Arrays.asList("we", "they"));

		LdaResult result = method.fit(docs);

		System.out.println("Topic Count: "+result.topicCount());
		
		for(int topicIndex = 0; topicIndex < result.topicCount(); ++topicIndex){
			 String topicSummary = result.topicSummary(topicIndex);
			 List<TupleTwo<String, Integer>> topKeyWords = result.topKeyWords(topicIndex, 10);
			 List<TupleTwo<Doc, Double>> topStrings = result.topDocuments(topicIndex, 5);

			 System.out.println("Topic #" + (topicIndex+1) + ": " + topicSummary);

			 for(TupleTwo<String, Integer> entry : topKeyWords){
			    String keyword = entry._1();
			    int score = entry._2();
			    System.out.println("Keyword: " + keyword + "(" + score + ")");
			 }

			 for(TupleTwo<Doc, Double> entry : topStrings){
			    double score = entry._2();
			    int docIndex = entry._1().getDocIndex();
			    String docContent = entry._1().getContent();
			    System.out.println("Doc (" + docIndex + ", " + score + ")): " + docContent);
			 }
			}
		
		
		
		System.out.println( "modeling done ----------");		
		
	}
	
}

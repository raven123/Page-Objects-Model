package com.test.storyRunner;

/**
 * Importing java libraries
 */

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.core.reporters.Format.HTML;
import static org.jbehave.core.reporters.Format.STATS;
import static org.jbehave.core.reporters.Format.TXT;
import static org.jbehave.core.reporters.Format.XML;
import static org.jbehave.web.selenium.WebDriverHtmlOutput.WEB_DRIVER_HTML;

import java.util.Arrays;
import java.util.List;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.embedder.EmbedderControls;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.failures.PendingStepStrategy;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.web.selenium.ContextView;
import org.jbehave.web.selenium.LocalFrameContextView;
import org.jbehave.web.selenium.SeleniumConfiguration;
import org.jbehave.web.selenium.SeleniumContext;
import org.jbehave.web.selenium.SeleniumContextOutput;
import org.jbehave.web.selenium.SeleniumStepMonitor;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;

import com.test.steps.SampleJBehaveSteps;

import de.codecentric.jbehave.junit.monitoring.JUnitReportingRunner;

/**
 * This is the Main/Runner class to map the feature files with the steps class
 * This class also parses the .feature file and invokes the corresponding step
 * class
 * 
 * @author CitiusTech
 */

@SuppressWarnings("nls")
@RunWith(JUnitReportingRunner.class)
public class Story_Runner extends JUnitStories {
	private SeleniumContext context = new SeleniumContext();
	private PendingStepStrategy pendingStepStrategy = new FailingUponPendingStep();
	private ContextView contextView = new LocalFrameContextView().sized(450,
			100);
	private CrossReference crossReference = new CrossReference().withJsonOnly()
			.withPendingStepStrategy(this.pendingStepStrategy)
			.withOutputAfterEachStory(true)
			.excludingStoriesWithNoExecutedScenarios(true);
	private SeleniumStepMonitor stepMonitor = new SeleniumStepMonitor(
			this.contextView, this.context,
			this.crossReference.getStepMonitor());
	private Format[] formats = new Format[] {
			new SeleniumContextOutput(this.context), CONSOLE, STATS, HTML, TXT,
			XML, WEB_DRIVER_HTML };
	private StoryReporterBuilder reporterBuilder = new StoryReporterBuilder()
			.withCodeLocation(codeLocationFromClass(Story_Runner.class))
			.withFailureTrace(true).withFailureTraceCompression(true)
			.withDefaultFormats().withFormats(this.formats)
			.withCrossReference(this.crossReference);
	
	/**
	 * Constructor to configure .story files
	 * 
	 */
	public Story_Runner() {
		EmbedderControls embedderControls = configuredEmbedder()
				.embedderControls();
		configuredEmbedder().useMetaFilters(Arrays.asList("-manual", "-skip"));
		embedderControls.useStoryTimeoutInSecs(900000);
		embedderControls.useThreads(1);
		embedderControls.doIgnoreFailureInStories(true);
		embedderControls.doIgnoreFailureInView(true);
		

	}

	/**
	 * @description:: This overridden method of JUnitStories is used to parse
	 *                the feature file using the GherkinStoryParser and
	 *                configure the test reports
	 * @return Configuration
	 */
	@Override
	public Configuration configuration() {

		return new SeleniumConfiguration()
				.useSeleniumContext(this.context)
				.usePendingStepStrategy(this.pendingStepStrategy)
				.useStoryControls(
						new StoryControls().doResetStateBeforeScenario(true))
				.useStepMonitor(this.stepMonitor)
				.useStoryLoader(new LoadFromClasspath(Story_Runner.class))
				.useStoryReporterBuilder(this.reporterBuilder);
	}

	/**
	 * @description:: This overridden method of JUnitStories is used to the
	 *                initialise the Steps file
	 * @return InjectableStepsFactory
	 */
	@Override
	public InjectableStepsFactory stepsFactory() {
		return new InstanceStepsFactory(configuration(),
				new SampleJBehaveSteps());
	}

	/**
	 * @description:: This overridden method of JUnitStories is used to return
	 *                all the feature file located at given path in the form of
	 *                list
	 * @return List
	 */
	@Override
	protected List<String> storyPaths() {
		return Arrays.asList("com/test/features/GoogleSearch.story");

	}

	/**
	 * Main Method
	 * 
	 * @param args
	 *            - Arguments from command line
	 * @throws Exception
	 *             - Exceptions
	 */
	public static void main(String[] args) throws Exception {
		JUnitCore core = new JUnitCore();
		core.run(Story_Runner.class);
		System.exit(0);
	}
}
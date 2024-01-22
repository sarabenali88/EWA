/**
 * Test for Image model
 * @author Sara Benali
 */
import { Image } from "@/models/Image";

describe("Image Model", () => {
    it("should create a sample image with custom values", () => {
        const id = 1;
        const customLaptop = "Lenovo";
        const customVersion = "1.0";
        const customStore = "Rotterdam";
        const customDate = "15-01-2024";
        const customStatus = Image.Status.TODO;
        const customRelease = Image.Release.NEW;
        const customProblem = Image.Problem.YES;
        const customCreatedWeek = 2;
        const customCreatedYear = 2024;
        const customName = "test";
        const customComment = "it works";
        const customImageMaker = "Sample Imagemaker";

        const sampleImage = Image.createSampleImage(id);

        sampleImage.laptop = customLaptop;
        sampleImage.version = customVersion;
        sampleImage.store = customStore;
        sampleImage.upDateDate = customDate;
        sampleImage.status = customStatus;
        sampleImage.release = customRelease;
        sampleImage.problem = customProblem;
        sampleImage.createdWeek = customCreatedWeek;
        sampleImage.createdYear = customCreatedYear;
        sampleImage.name = customName;
        sampleImage.comment = customComment;
        sampleImage.imageMaker = customImageMaker

        expect(sampleImage.id).toBe(id);
        expect(sampleImage.laptop).toBe(customLaptop);
        expect(sampleImage.version).toBe(customVersion);
        expect(sampleImage.store).toBe(customStore);
        expect(sampleImage.upDateDate).toBe(customDate);
        expect(sampleImage.status).toBe(customStatus);
        expect(sampleImage.release).toBe(customRelease);
        expect(sampleImage.problem).toBe(customProblem);
        expect(sampleImage.createdWeek).toBe(customCreatedWeek);
        expect(sampleImage.createdYear).toBe(customCreatedYear);
        expect(sampleImage.name).toBe(customName);
        expect(sampleImage.comment).toBe(customComment);
        expect(sampleImage.imageMaker).toBe(customImageMaker);


    });
});
